package com.pos.pos.commons.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DataValidation {

    private static DataValidation dataValidation;
    private DataValidation(){}

    public static DataValidation getDataValidation(){
        if(dataValidation==null){
            dataValidation=new DataValidation();
        }
        return dataValidation;
    }

    private final static String EMAIL_PATRON="^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+";
    private final static String PHONE_PATRON="^(\\+\\d{1,3}( )?)?[0-9]+";
    private final static String NAME_PATRON="^([a-zA-Zp{L} .'-]+){3}$";
    private final static String ONLY_LETTERS_PATRON="^[a-zA-Z]+";
    private final static String ONLY_NUMBERS_PATRON="^[0-9]+";
    private final static String ONLY_UPPER_CASE_PATRON="^[A-Z]+";
    private final static String ONLY_LOWER_CASE_PATRON="^[a-z]+";
    private final static String LETTERS_AND_NUMBERS_PATRON="^[a-zA-Z0-9]+";
    private static final String CODE_PATRON ="^[0-9]+[-0-9]+";
    private static final String WEB_PAGE_PATRON ="^https?:\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$";
    private static final String PASSWORD_PATRON ="^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$" ;

    /**
     * Metodo que valida si una cadena sigue el formato de un email
     * @param email la cadena de texto
     * @return boolean que indica si la cadena es un email o no.
     */
    public boolean validateEmail(String email){
        return Pattern.compile(EMAIL_PATRON).matcher(email).matches();
    }

    /**
     * Metodo que valida si un nombre tiene solamente letras espacios y un punto al final
     * @param data la cadena de texto
     * @return boolean que indica si la cadena es un nombre o no.
     */
    public boolean validateName(String data){
        return Pattern.compile(NAME_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si un numero de telefono es valido.
     * @param data la cadena de texto
     * @return boolean que indica si la cadena sigue el formato de un telefono o no.
     */
    public boolean validatePhone(String data){
        return Pattern.compile(PHONE_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si una cadena esta compuesto por solo letras
     * @param data la cadena de texto
     * @return boolean que indica si la cadena solamente tiene letras o no.
     */
    public boolean validateOnlyLetters(String data){
        return Pattern.compile(ONLY_LETTERS_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si una cadena tiene solo numeros
     * @param data la cadena de texto
     * @return boolean que indica si la cadena solamente tiene numeros o no.
     */
    public boolean validateOnlyNumbers(String data){
        return Pattern.compile(ONLY_NUMBERS_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si un texto sigue el formato de numeros y guiones
     * @param data la cadena de texto
     * @return boolean que indica si la cadena solamente tiene solo numeros y guiones
     */
    public boolean validateCode(String data) {
        return Pattern.compile(CODE_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si una cadena tiene solo letras mayúsculas
     * @param data la cadena de texto
     * @return boolean que indica si la cadena esta compuesta de solamente letras mayúsculas.
     */
    public boolean validateOnlyUpperCase(String data){
        return Pattern.compile(ONLY_UPPER_CASE_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si una cadena tiene solo letras minusculas
     * @param data la cadena de texto
     * @return boolean que indica si la cadena esta compuesta de solamente letras minusculas.
     */
    public boolean validateOnlyLowerCase(String data){
        return Pattern.compile(ONLY_LOWER_CASE_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si una cadena tiene solo letras y numeros pero no caracteres especiales
     * @param data la cadena de texto
     * @return boolean que indica si la cadena esta compuesta de solamente letras y numeros.
     */
    public boolean validateOnlyLettersAndNumbers(String data){
        return Pattern.compile(LETTERS_AND_NUMBERS_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si una cadena sigue el formato de una url
     * @param data la cadena de texto
     * @return boolean que indica si la cadena sigue el formato de una url
     */
    public boolean validateWebPage(String data) {
       return Pattern.compile(WEB_PAGE_PATRON).matcher(data).matches();
    }

    /**
     * Metodo que valida si una contraseña tiene entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula.
     * @param data la cadena de texto
     * @return boolean que indica si la contraseña cumple o no con el formato
     */
    public boolean validatePassword(String data) {
        return Pattern.compile(PASSWORD_PATRON).matcher(data).matches();
    }


    /**
     * Metodo que valida si una cadena contiene informacion o solo esta vacia.
     * @param data la cadena de texto
     * @return boolean que indica si la cadena no tiene contenido
     */
    public boolean validateNoEmptyData(String data){
        return data.length()>0;
    }

    /**
     * Metodo que valida si una cadena tiene una longitud esperada
     * @param data la cadena de texto
     * @return boolean que indica si la cadena tiene cierta canitdad de caracteres esperada.
     */
    public boolean validateSize(String data,int expected){
        return data.length()==expected;
    }

}
