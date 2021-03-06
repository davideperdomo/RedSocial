/**
 * ui Interfaz del Usuario
 */
package ui;

import Excepcion.ExcepcionSistema;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import servicio.Servicios;

/** 
 * Clase Interfaz del Usuario
 * Conecta la aplicacion con el usuario
 * @author David
 */
public class UI {
    Servicios servicio = new Servicios();BufferedReader lectura= new BufferedReader(new InputStreamReader(System.in));
    int m;
    int opcion;
    /** Menú del usuario
     * 
     * @throws IOException 
     */
    public void usuarioMenu() throws IOException {
        System.out.println("Ingrese Nick");
        String nick;
        nick = lectura.readLine();
        do{
            System.out.println("Opciones Usuario:1.Comentar-2.Subir foto-3.Busquedas");
            try {opcion = Integer.parseInt(lectura.readLine());
            } catch (NumberFormatException ex) {System.out.println("Opcion invalida");}
            switch(opcion){
                case 1:
                    servicio.comentar(nick);
                    servicio.printComentarios(nick);
                    break;
                case 2:
                    servicio.subirFoto(nick);
                    break;
                case 3:
                    busquedas();
                    break;
            }
            servicio.serializar();
            System.out.println("1.Continuar menú de usuario");
            m = Integer.parseInt(lectura.readLine());
        }while(m==1);
    }
    /** Menú de busquedas
     * 
     * @throws IOException 
     */
    public void busquedas() throws IOException{
        do{try {
            System.out.println("Menu Busquedas: 1.Buscar Usuario-2.Listar Comentarios-3.Listar Fotos-4.Buscar palabra en comentarios");
            
                opcion = Integer.parseInt(lectura.readLine());
                switch(opcion){
                case 1:
                    servicio.buscarUsuarios();
                    break;
                case 2:
                    servicio.listarComentarios();
                    break;
                case 3:
                    servicio.listarFotos();
                    break;
                case 4:
                    servicio.buscar();
                    break;
                }
            } catch (NumberFormatException ex) {System.out.println("Opcion invalida");            }
            
            System.out.println("1.Continuar menú de busquedas");
            m = Integer.parseInt(lectura.readLine());
        }while(m==1);
    }
    /** Menú de la aplicación
     * 
     * @throws IOException
     * @throws ExcepcionSistema 
     */
    public void menu() throws IOException, ExcepcionSistema{
        File file = new File("DATABASE.txt");
        if(file.exists()){
        servicio.deserializar();
        }
        do{
            try{
            System.out.println("1.Registrarse-2.Ingresar a Red Social-3.Busquedas-4.Reiniciar data");
            opcion = Integer.parseInt(lectura.readLine());
            switch(opcion){
                case 1 :
                    servicio.crearUsuario();
                    servicio.serializar();
                    break;
                case 2 :
                    usuarioMenu();
                    break;
                case 3:
                    busquedas();
                    break;
                case 4:
                    file.delete();
            }}catch(NumberFormatException e){System.out.println("Opcion invalida");}
            System.out.println("1.Continuar menú inicial");
            try {m = Integer.parseInt(lectura.readLine());
            } catch (NumberFormatException ex) {System.out.println("Opcion invalida");            }
        }while(m==1);
        
    }

}
