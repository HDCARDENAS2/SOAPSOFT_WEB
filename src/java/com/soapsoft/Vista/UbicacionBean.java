/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Vista;

import com.soapsoft.service.TbUbicacion;
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
public class UbicacionBean {

    
    
   
     public List<TbUbicacion> lista_ubicacion;
     public TbUbicacion o_ubicacion;
     public int id;
     public String v_descripcion;
     public String creadoPor="JA";
     public String modificadoPor="JA";
    /**
     * Creates a new instance of VentasBean
     */
    
    public void init() {
        
        o_ubicacion=new TbUbicacion();
        lista_ubicacion=new ArrayList<>();
        lista_ubicacion = consultarTodosUbicacion();

    }

    public List<TbUbicacion> getLista_ubicacion() {
        return lista_ubicacion;
    }

    public void setLista_ubicacion(List<TbUbicacion> lista_ubicacion) {
        this.lista_ubicacion = lista_ubicacion;
    }

    public TbUbicacion getO_ubicacion() {
        return o_ubicacion;
    }

    public void setO_ubicacion(TbUbicacion o_ubicacion) {
        this.o_ubicacion = o_ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TbUbicacion> getTb_o_ubicacion() {
      if (lista_ubicacion == null){
	    	try {
				lista_ubicacion = consultarTodosUbicacion();
			} catch (Exception e) {				
				e.printStackTrace();
			}
	    }
		return lista_ubicacion;
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
    
   public void crear_ubicacion()
   {
       fnInsertar(v_descripcion, creadoPor);
       mensaje("Info","Ubicación Insertada con exito!");
       v_descripcion="";
       lista_ubicacion=new ArrayList<>();
       lista_ubicacion = consultarTodosUbicacion();
       
   }
   
  
    
    public void mensaje(String titulo,String mensaje){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(titulo, mensaje));
    }

    private static String fnInsertar(java.lang.String descripcion, java.lang.String creadoPor) {
        com.soapsoft.service.SVRUBICACION_Service service = new com.soapsoft.service.SVRUBICACION_Service();
        com.soapsoft.service.SVRUBICACION port = service.getSVRUBICACIONPort();
        return port.fnInsertar(descripcion, creadoPor);
    }

    private static java.util.List<com.soapsoft.service.TbUbicacion> consultarTodosUbicacion() {
        com.soapsoft.service.SVRUBICACION_Service service = new com.soapsoft.service.SVRUBICACION_Service();
        com.soapsoft.service.SVRUBICACION port = service.getSVRUBICACIONPort();
        return port.consultarTodosUbicacion();
    }

    private static String fnModificar(int id, java.lang.String descripcion, java.lang.String modificadoPor) {
        com.soapsoft.service.SVRUBICACION_Service service = new com.soapsoft.service.SVRUBICACION_Service();
        com.soapsoft.service.SVRUBICACION port = service.getSVRUBICACIONPort();
        return port.fnModificar(id, descripcion, modificadoPor);
    }

   
    
    
    
    
        
}
