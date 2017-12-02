/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Vista;

import com.soapsoft.Vista.Model.DetalleFacturaVenta;
import com.soapsoft.service.TbClientes;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@ManagedBean
@ViewScoped
public class VentasBean {

    public TbClientes  o_cliente;
    public int id_cliente;
    public ArrayList<SelectItem> o_productos ;
    public DetalleFacturaVenta detalle;
     public ArrayList<DetalleFacturaVenta> o_deltalle_factura;
     public String v_Observacion;

    /**
     * Creates a new instance of VentasBean
     */
    public VentasBean() {
        o_cliente =  new TbClientes();
         o_productos = new ArrayList<>();
         detalle  = new DetalleFacturaVenta();
         o_deltalle_factura = new ArrayList<>();
    }
    
   
    public void v_consultaCliente(){
        
        TbClientes  resultado =  consultarCliente(id_cliente);
         detalle  = new DetalleFacturaVenta();
        
        if(resultado == null){
                o_productos  = new ArrayList<>();
                mensaje("Info","El usuario con el nit no existe");
        }else{
             o_cliente        = resultado;
             o_productos = Consultar_Productos();
        }
        
    }
    
      public void eliminar_detalle(DetalleFacturaVenta detalle_temp) {
           o_deltalle_factura.remove(detalle_temp);
       }

      
      public void v_hacer_venta(){
          int id_venta = fnInsertarFacturaVenta(v_Observacion,o_cliente.getId(),"hernan");
          if( id_venta > 0){
              for (DetalleFacturaVenta detalle_temp  : o_deltalle_factura) {
                    fnInsertarDetalleFacturaVenta(
                            id_venta,
                            detalle_temp.getIdProductoTerminado(),
                            detalle_temp.getCantidad(),
                            detalle_temp.getVlorIva(),
                            detalle_temp.getTipoIva(),
                            detalle_temp.getVlorUnitario(),
                            detalle_temp.getVlorTotal(),
                            "hernan"
                            );
              }
              
              
              
             o_cliente =  new TbClientes();
             detalle  = new DetalleFacturaVenta();
             o_deltalle_factura = new ArrayList<>();
             v_Observacion  = null;
             id_cliente = 0;
             
              mensaje("Info","Venta insertada con exito");
             
          }
      }
      
  
    public void v_addicionar_detalle(){
          o_deltalle_factura.add(detalle);
          detalle  = new DetalleFacturaVenta();
    }
    
    
    public void mensaje(String titulo,String mensaje){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(titulo, mensaje));
    }
    
    
     public ArrayList<SelectItem> Consultar_Productos() {
       
        ArrayList<SelectItem> items = new ArrayList<>();
        
        java.util.List<com.soapsoft.service.TbProductoTerminado> o_productoss =  consultarProductos();
         
        for (com.soapsoft.service.TbProductoTerminado obj : o_productoss) {
            items.add(new SelectItem(obj.getId(), obj.getDescripcion()));
        }
        
        return items;
    }

    public String getV_Observacion() {
        return v_Observacion;
    }

    public void setV_Observacion(String v_Observacion) {
        this.v_Observacion = v_Observacion;
    }
  
    public ArrayList<DetalleFacturaVenta> getO_deltalle_factura() {
        return o_deltalle_factura;
    }

    public void setO_deltalle_factura(ArrayList<DetalleFacturaVenta> o_deltalle_factura) {
        this.o_deltalle_factura = o_deltalle_factura;
    }
      
    public TbClientes getO_cliente() {
        return o_cliente;
    }

    public void setO_cliente(TbClientes o_cliente) {
        this.o_cliente = o_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public DetalleFacturaVenta getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleFacturaVenta detalle) {
        this.detalle = detalle;
    }
   

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

       public ArrayList<SelectItem> getO_productos() {
            return o_productos;
        }

        public void setO_productos(ArrayList<SelectItem> o_productos) {
            this.o_productos = o_productos;
        }

    private static java.util.List<com.soapsoft.service.TbProductoTerminado> consultarProductos() {
        com.soapsoft.service.SVRPRODUCTOTERMINADO_Service service = new com.soapsoft.service.SVRPRODUCTOTERMINADO_Service();
        com.soapsoft.service.SVRPRODUCTOTERMINADO port = service.getSVRPRODUCTOTERMINADOPort();
        return port.consultarProductos();
    }

    private static TbClientes consultarCliente(int id) {
        com.soapsoft.service.SVRCLIENTES_Service service = new com.soapsoft.service.SVRCLIENTES_Service();
        com.soapsoft.service.SVRCLIENTES port = service.getSVRCLIENTESPort();
        return port.consultarCliente(id);
    }

    private static int fnInsertarFacturaVenta(java.lang.String observacion, int idCliente, java.lang.String creadoPor) {
        com.soapsoft.service.SVRFACTURAVENTA_Service service = new com.soapsoft.service.SVRFACTURAVENTA_Service();
        com.soapsoft.service.SVRFACTURAVENTA port = service.getSVRFACTURAVENTAPort();
        return port.fnInsertarFacturaVenta(observacion, idCliente, creadoPor);
    }

    private static int fnInsertarDetalleFacturaVenta(int idFacturaVenta, int idProducto, int cantidad, int vlorIva, java.lang.String tipoIva, int vlorUnitario, int vlorTotal, java.lang.String creadoPor) {
        com.soapsoft.service.SRVDETALLEFACTURAVENTA_Service service = new com.soapsoft.service.SRVDETALLEFACTURAVENTA_Service();
        com.soapsoft.service.SRVDETALLEFACTURAVENTA port = service.getSRVDETALLEFACTURAVENTAPort();
        return port.fnInsertarDetalleFacturaVenta(idFacturaVenta, idProducto, cantidad, vlorIva, tipoIva, vlorUnitario, vlorTotal, creadoPor);
    }
    
        
}
