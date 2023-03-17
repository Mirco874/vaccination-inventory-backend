package com.pos.pos.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgConfigPropertyType;

@Getter
@AllArgsConstructor
public enum HttpResponseMessage {
    FIND_SUCCESSFUL("Busqueda exitosa"),
    PERSIST_SUCCESSFUL("Se registro correctamente"),
    UPDATE_SUCCESSFUL("Se actualizo correctamente"),
    DELETE_SUCCESSFUL("Se elimino correctamente"),
    NOT_FOUND_RECORD("No se encontro el registro"),
    PERSON_RECORD_EXISTENT("El documento de la persona ya se encuentra registrado"),
    NOT_FOUND_USUARIO("No se encontro el registro del usuario: "),
    FAIL_TO_SAVE("No se guardo correctamente"),
    FAIL_TO_UPDATE("No se actualizo el registro ingresado"),
    PERSIST_FAILED("No se guardo el nuevo registro, verifique los datos enviados"),
    NOT_FOUND("no se encontro el registro solicitado"),
    FAIL_TO_DELETE( "no se pudo eliminar el registro") ;
    private final String value;
}
