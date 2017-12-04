/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Vista;

import com.soapsoft.service.TbProduccion;
import java.util.ArrayList;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JA
 */
@ManagedBean
@SessionScoped
public class ProduccionBean {

    
    
   
     public List<TbProduccion> lista_produccion;
     public TbProduccion o_Produccion;
     public String id_dpto_produccion ;
     public String v_descripcion;
     public String creadoPor="JA";
     public String modificadoPor="JA";
    /**
     * Creates a new instance of VentasBean
     */
    
    public void init() {        
        o_Produccion=new TbProduccion();
        lista_produccion=new ArrayList<>();
        lista_produccion = consultarTodosProduccion();
    }

    public List<TbProduccion> getLista_produccion() {
        return lista_produccion;
    }

    public void setLista_produccion(List<TbProduccion> lista_produccion) {
        this.lista_produccion = lista_produccion;
    }

    public TbProduccion getO_produccion() {
        return o_Produccion;
    }

    public void setO_produccion(TbProduccion o_produccion) {
        this.o_Produccion = o_produccion;
    }

    public String getId_dpto_produccion() {
        return id_dpto_produccion;
    }

    public void setId_dpto_produccion(String id) {
        this.id_dpto_produccion = id;
    }

    public List<TbProduccion> getTb_o_produccion() {
      if (lista_produccion == null){
	    	try {
				lista_produccion = consultarTodosProduccion();
			} catch (Exception e) {				
				e.printStackTrace();
			}
	    }
		return lista_produccion;
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

   

    public String getV_descripcion() {
        return v_descripcion;
    }

    public void setV_descripcion(String v_descripcion) {
        this.v_descripcion = v_descripcion;
    }
    
    
    
    
   public void crear_produccion()
   {
       fnInsertar(id_dpto_produccion, creadoPor, v_descripcion);
       mensaje("Info","Se ha insertado con exito en produccion");
       v_descripcion="";
       lista_produccion=new ArrayList<>();
       lista_produccion = consultarTodosProduccion();
       
   }
   
  
    
    public void mensaje(String titulo,String mensaje){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(titulo, mensaje));
    }

    private static String fnInsertar(java.lang.String id_dpto_produccion,java.lang.String creadoPor, java.lang.String descripcion) {
        com.soapsoft.service.SVRPRODUCCION_Service service = new com.soapsoft.service.SVRPRODUCCION_Service();
        com.soapsoft.service.SVRPRODUCCION port = service.getSVRPRODUCCIONPort();
        return port.fnInsertarPr(id_dpto_produccion, creadoPor, descripcion);
    }

    private static java.util.List<com.soapsoft.service.TbProduccion> consultarTodosProduccion() {
        com.soapsoft.service.SVRPRODUCCION_Service service = new com.soapsoft.service.SVRPRODUCCION_Service();
        com.soapsoft.service.SVRPRODUCCION port = service.getSVRPRODUCCIONPort();
        return port.fnConsultarPr();
    }

    private static String fnModificar(int id, java.lang.String descripcion, java.lang.String modificadoPor) {
        com.soapsoft.service.SVRUBICACION_Service service = new com.soapsoft.service.SVRUBICACION_Service();
        com.soapsoft.service.SVRUBICACION port = service.getSVRUBICACIONPort();
        return port.fnModificar(id, descripcion, modificadoPor);
    }

   
    
    
    
    
        
}
