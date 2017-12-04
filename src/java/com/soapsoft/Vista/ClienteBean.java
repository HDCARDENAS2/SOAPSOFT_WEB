/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Vista;

import com.soapsoft.service.TbClientes;
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
public class ClienteBean {

    
    
   
     public List<TbClientes> lista_cliente;
     public TbClientes o_clientes;
     public int id;
     public String v_nit="nit";
     public String v_razon_social="nombre";
     public String v_tel="0000000";
     public String v_cel="0000000";
     public boolean v_estado=true;
     public String v_direccion="direccion";
     public int contacto=1;
     
     public String creadoPor="JA";
     public String modificadoPor="JARTEAGA 77";
    /**
     * Creates a new instance of UbicacionBean
     */

    public void init() {
        
        o_clientes=new TbClientes();
        lista_cliente=new ArrayList<>();
        lista_cliente = consultarTodosClientes();

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

    public List<TbClientes> getLista_cliente() {
          if (lista_cliente == null){
	    	try {
				lista_cliente = consultarTodosClientes();
			} catch (Exception e) {				
				e.printStackTrace();
			}
	    }
		return lista_cliente;
    }

    public void setLista_cliente(List<TbClientes> lista_cliente) {
        this.lista_cliente = lista_cliente;
    }

    public TbClientes getO_clientes() {
        return o_clientes;
    }

    public void setO_clientes(TbClientes o_clientes) {
        this.o_clientes = o_clientes;
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

    

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

   

    
   public void crear_cliente()
   {

       fnInsertar(v_nit, v_razon_social, v_tel, v_cel, contacto, v_estado, v_direccion, creadoPor);
       mensaje("Información","Cliente creado con exito!");
       lista_cliente=new ArrayList<>();
       lista_cliente = consultarTodosClientes();
       
   }
   public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  
   public void onRowEdit(RowEditEvent event) {
   
       setId(((TbClientes) event.getObject()).getId());
       setV_nit(((TbClientes) event.getObject()).getNit());
       setV_razon_social(((TbClientes) event.getObject()).getRazonSocial());
       setV_tel(((TbClientes) event.getObject()).getTelefono());
       setV_cel(((TbClientes) event.getObject()).getCelular());
       setV_direccion(((TbClientes) event.getObject()).getDireccion());
    
        fnModificar(id, v_nit, v_razon_social, v_tel, v_cel, v_direccion, modificadoPor);
       
         lista_cliente=new ArrayList<>();
         lista_cliente = consultarTodosClientes();
       
        FacesMessage msg = new FacesMessage("Cliente Modificado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
   public void deleteUbicacion(int id)
   {
       try
       {
       for (TbClientes tbClientes : lista_cliente) {
            if(tbClientes.getId()== id){
                fnBorrar(id);
            
               break;
            }
       }
                lista_cliente=new ArrayList<>();
                lista_cliente = consultarTodosClientes();
                FacesMessage msg = new FacesMessage("Cliente Eliminado!");
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

  

    private static String fnModificar(int id, java.lang.String nit, java.lang.String razonSocial, java.lang.String telefono, java.lang.String celular, java.lang.String direccion, java.lang.String modificadoPor) {
        com.soapsoft.service.SVRCLIENTES_Service service = new com.soapsoft.service.SVRCLIENTES_Service();
        com.soapsoft.service.SVRCLIENTES port = service.getSVRCLIENTESPort();
        return port.fnModificar(id, nit, razonSocial, telefono, celular, direccion, modificadoPor);
    }

    private static String fnBorrar(int id) {
        com.soapsoft.service.SVRCLIENTES_Service service = new com.soapsoft.service.SVRCLIENTES_Service();
        com.soapsoft.service.SVRCLIENTES port = service.getSVRCLIENTESPort();
        return port.fnBorrar(id);
    }

    private static java.util.List<com.soapsoft.service.TbClientes> consultarTodosClientes() {
        com.soapsoft.service.SVRCLIENTES_Service service = new com.soapsoft.service.SVRCLIENTES_Service();
        com.soapsoft.service.SVRCLIENTES port = service.getSVRCLIENTESPort();
        return port.consultarTodosClientes();
    }

    private static String fnInsertar(java.lang.String nit, java.lang.String razonSocial, java.lang.String telefono, java.lang.String celular, int contacto, boolean estado, java.lang.String direccion, java.lang.String creadoPor) {
        com.soapsoft.service.SVRCLIENTES_Service service = new com.soapsoft.service.SVRCLIENTES_Service();
        com.soapsoft.service.SVRCLIENTES port = service.getSVRCLIENTESPort();
        return port.fnInsertar(nit, razonSocial, telefono, celular, contacto, estado, direccion, creadoPor);
    }

  

}
