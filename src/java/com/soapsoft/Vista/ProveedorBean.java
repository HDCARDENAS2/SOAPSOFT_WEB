/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Vista;

import com.soapsoft.service.TbClientes;
import com.soapsoft.services.TbProveedor;
import java.util.ArrayList;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author JA
 */
@ManagedBean
@SessionScoped
public class ProveedorBean {

    
    
   
     public List<TbProveedor> lista_proveedor;
     public TbProveedor o_proveedor;
     public int id;
     public String v_nit="nit";
     public String v_razon_social="nombre";
     public String v_tel="0000000";
     public String v_cel="0000000";
     public boolean v_estado=true;
     public String v_direccion="direccion";
     public String contacto="contacto";
     
     public String creadoPor="JA";
     public String modificadoPor="JARTEAGA 77";
    /**
     * Creates a new instance of UbicacionBean
     */

    public void init() {
        
        o_proveedor=new TbProveedor();
        lista_proveedor=new ArrayList<>();
        lista_proveedor = fnBuscarAllProve();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }
 
    public String getV_nit() {
        return v_nit;
    }

    public void setV_nit(String v_nit) {
        this.v_nit = v_nit;
    }

    public String getV_razon_social() {
        return v_razon_social;
    }

    public void setV_razon_social(String v_razon_social) {
        this.v_razon_social = v_razon_social;
    }

    public String getV_tel() {
        return v_tel;
    }

    public void setV_tel(String v_tel) {
        this.v_tel = v_tel;
    }

    public String getV_cel() {
        return v_cel;
    }

    public void setV_cel(String v_cel) {
        this.v_cel = v_cel;
    }

    public boolean isV_estado() {
        return v_estado;
    }

    public void setV_estado(boolean v_estado) {
        this.v_estado = v_estado;
    }

    public String getV_direccion() {
        return v_direccion;
    }

    public void setV_direccion(String v_direccion) {
        this.v_direccion = v_direccion;
    }

    
    
    public List<TbProveedor> getLista_proveedor() {
        if (lista_proveedor == null){
	    	try {
				lista_proveedor = fnBuscarAllProve();
			} catch (Exception e) {				
				e.printStackTrace();
			}
	    }
		return lista_proveedor;
    }

    public void setLista_proveedor(List<TbProveedor> lista_proveedor) {
        this.lista_proveedor = lista_proveedor;
    }

    public TbProveedor getO_proveedor() {
        return o_proveedor;
    }

    public void setO_proveedor(TbProveedor o_proveedor) {
        this.o_proveedor = o_proveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

  
    
   public void crear_proveedor()
   {

       fnInsertarProve(v_nit, v_razon_social, contacto, v_tel, v_cel, v_direccion, v_estado, creadoPor);
       mensaje("Información","Proveedor creado con exito!");
       lista_proveedor=new ArrayList<>();
       lista_proveedor = fnBuscarAllProve();
       
   }
   public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  
   public void onRowEdit(RowEditEvent event) {
   
       setId(((TbProveedor) event.getObject()).getId());
       setV_nit(((TbProveedor) event.getObject()).getNit());
       setV_razon_social(((TbProveedor) event.getObject()).getRazonSocial());
       setV_tel(((TbProveedor) event.getObject()).getTelefono());
       setV_cel(((TbProveedor) event.getObject()).getCelular());
       setContacto(((TbProveedor) event.getObject()).getContacto());
       setV_direccion(((TbProveedor) event.getObject()).getDireccion());
    
        fnModificarProve(id, v_nit, v_razon_social,contacto, v_tel, v_cel, v_direccion, modificadoPor);
       
         lista_proveedor=new ArrayList<>();
         lista_proveedor = fnBuscarAllProve();
       
        FacesMessage msg = new FacesMessage("Proveedor Modificado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
   public void deleteproveedor(int id)
   {
       try
       {
       for (TbProveedor tbProveedor : lista_proveedor) {
            if(tbProveedor.getId()== id){
                fnBorrarProve(id);
            
               break;
            }
       }
                lista_proveedor=new ArrayList<>();
                lista_proveedor = fnBuscarAllProve();
                FacesMessage msg = new FacesMessage("Proveedor Eliminado!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
       }catch(Exception e)
       {
           FacesMessage msg = new FacesMessage(String.valueOf(e));
           FacesContext.getCurrentInstance().addMessage(null, msg);
       }

   }
    public void mensaje(String titulo,String mensaje){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(titulo, mensaje));
    }

    private static String fnInsertarProve(java.lang.String nit, java.lang.String razonSocial, java.lang.String contacto, java.lang.String telefono, java.lang.String celular, java.lang.String direccion, boolean estado, java.lang.String creadoPor) {
        com.soapsoft.services.SVRPROVEEDORES_Service service = new com.soapsoft.services.SVRPROVEEDORES_Service();
        com.soapsoft.services.SVRPROVEEDORES port = service.getSVRPROVEEDORESPort();
        return port.fnInsertarProve(nit, razonSocial, contacto, telefono, celular, direccion, estado, creadoPor);
    }

    private static String fnModificarProve(int id, java.lang.String nit, java.lang.String razonSocial, java.lang.String contacto, java.lang.String telefono, java.lang.String celular, java.lang.String direccion, java.lang.String modificadoPor) {
        com.soapsoft.services.SVRPROVEEDORES_Service service = new com.soapsoft.services.SVRPROVEEDORES_Service();
        com.soapsoft.services.SVRPROVEEDORES port = service.getSVRPROVEEDORESPort();
        return port.fnModificarProve(id, nit, razonSocial, contacto, telefono, celular, direccion, modificadoPor);
    }

    private static String fnBorrarProve(int id) {
        com.soapsoft.services.SVRPROVEEDORES_Service service = new com.soapsoft.services.SVRPROVEEDORES_Service();
        com.soapsoft.services.SVRPROVEEDORES port = service.getSVRPROVEEDORESPort();
        return port.fnBorrarProve(id);
    }

    private static java.util.List<com.soapsoft.services.TbProveedor> fnBuscarAllProve() {
        com.soapsoft.services.SVRPROVEEDORES_Service service = new com.soapsoft.services.SVRPROVEEDORES_Service();
        com.soapsoft.services.SVRPROVEEDORES port = service.getSVRPROVEEDORESPort();
        return port.fnBuscarAllProve();
    }

  

  

}
