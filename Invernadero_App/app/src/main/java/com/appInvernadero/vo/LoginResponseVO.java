package com.appInvernadero.vo;

import java.util.List;

/**
 * Created on 02/08/2017.
 */

public class LoginResponseVO {

    private int codigo;
    private String descripcionCodigo;
    private List<String> permisoUsuario;

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDescripcionCodigo() {
        return descripcionCodigo;
    }
    public void setDescripcionCodigo(String descripcionCodigo) {
        this.descripcionCodigo = descripcionCodigo;
    }
    public List<String> getPermisoUsuario() {
        return permisoUsuario;
    }
    public void setPermisoUsuario(List<String> permisoUsuario) {
        this.permisoUsuario = permisoUsuario;
    }
}
