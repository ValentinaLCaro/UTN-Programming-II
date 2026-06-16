/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Main;

import entities.Categoria;
import entities.DetallePedido;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import enums.Rol;
import exception.NegocioException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import services.CategoriaService;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;
import utilities.Validador;

/**
 *
 * @author vale
 */
public class Menu {
    private static final CategoriaService categoriaService = new CategoriaService();
    private static final ProductoService productoService = new ProductoService();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final PedidoService pedidoService = new PedidoService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== SISTEMA DE PEDIDOS (FOOD STORE) ==="); 
            System.out.println("1. Categorias"); 
            System.out.println("2. Productos"); 
            System.out.println("3. Usuarios"); 
            System.out.println("4. Pedidos");
            System.out.println("0. Salir"); 
            
            try {
                opcion = Validador.leerEntero(scanner, "Seleccione una opcion: "); 
                Validador.validarOpcionMenu(opcion, 0, 4); 
                switch (opcion) {
                    case 1 -> menuCategorias(scanner); 
                    case 2 -> menuProductos(scanner);
                    case 3 -> menuUsuarios(scanner);
                    case 4 -> menuPedidos(scanner);
                    case 0 -> System.out.println("¡Gracias por utilizar Food Store!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }
    
    // ======================
    // 1. SUBMENU CATEGORIAS
    // ======================
    private static void menuCategorias(Scanner scanner) {
        while (true) {
            System.out.println("\n--- GESTION DE CATEGORIAS ---");
            System.out.println("1. Listar categorias");
            System.out.println("2. Crear categoria");
            System.out.println("3. Editar categoria");
            System.out.println("4. Eliminar categoria");
            System.out.println("0. Volver al Menu Principal");
            
            int opcion = Validador.leerEntero(scanner, "Seleccione una opcion: ");
            if (opcion == 0) {
                break;
            }

            try {
                Validador.validarOpcionMenu(opcion, 1, 4);
                switch (opcion) {
                    case 1:
                        ArrayList<Categoria> activas = categoriaService.obtenerCategoriasActivas();
                        if (activas.isEmpty()) {
                            System.out.println("No hay categorias cargadas.");
                        } else {
                            for (Categoria cat : activas) {
                                System.out.println(cat);
                            }
                        }
                        break;
                        
                    case 2:
                        System.out.print("Ingrese nombre de la categoria: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese descripcion: ");
                        String desc = scanner.nextLine();
                        
                        Categoria nueva = new Categoria(nombre, desc, false, LocalDateTime.now());
                        categoriaService.crearCategoria(nueva);
                        System.out.println("Categoria creada con exito. ID asignado: " + nueva.getId());
                        break;
                        
                    case 3:
                        long id = Validador.leerEntero(scanner, "Ingrese ID de la categoria a editar: ");
                        Categoria cat = categoriaService.buscarPorId(id);
                        
                        System.out.print("Nuevo nombre (Actual: " + cat.getNombre() + " - Presione Enter para mantener): ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nueva descripción (Actual: " + cat.getDescripcion() + " - Presione Enter para mantener): ");
                        String nuevaDesc = scanner.nextLine();
                        
                        // Solo actualiza si el usuario ingresó texto real
                        if (!nuevoNombre.trim().isEmpty()) {
                            cat.setNombre(nuevoNombre);
                        }
                        if (!nuevaDesc.trim().isEmpty()) {
                            cat.setDescripcion(nuevaDesc);
                        }
                        System.out.println("Categoria actualizada con exito.");
                        break;
                        
                    case 4:
                        long idEliminar = Validador.leerEntero(scanner, "Ingrese ID de la categoria a eliminar: ");
                        System.out.print("¿Esta seguro de eliminar esta categoria? (S/N): ");
                        String conf = scanner.nextLine();
                        if (conf.equalsIgnoreCase("S")) {
                            categoriaService.eliminarCategoria(idEliminar);
                            System.out.println("Categoria dada de baja logicamente.");
                        } else {
                            System.out.println("Operacion cancelada.");
                        }
                        break;
                }
            } catch (NegocioException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // =====================
    // 2. SUBMENU PRODUCTOS
    // =====================
    private static void menuProductos(Scanner scanner) {
        while (true) {
            System.out.println("\n--- GESTION DE PRODUCTOS ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Crear producto");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver al Menu Principal");
            
            int opcion = Validador.leerEntero(scanner, "Seleccione una opcion: ");
            if (opcion == 0) {
                break;
            }

            try {
                Validador.validarOpcionMenu(opcion, 1, 4);
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- OPCIONES DE LISTADO ---");
                        System.out.println("1. Listado general");
                        System.out.println("2. Filtrar por categoria");
                        int tipoListado = Validador.leerEntero(scanner, "Seleccione una opcion: ");

                        ArrayList<Producto> productosAMostrar;
                        if (tipoListado == 2) {
                            long catId = Validador.leerEntero(scanner, "Ingrese ID de la categoria: ");
                            // validar que la categorí+ia exista+e
                            categoriaService.buscarPorId(catId); 
                            productosAMostrar = productoService.obtenerProductosPorCategoria(catId);
                        } else {
                            productosAMostrar = productoService.obtenerProductosActivos();
                        }

                        if (productosAMostrar.isEmpty()) {
                            System.out.println("No se encontraron productos para mostrar.");
                        } else {
                            for (Producto prod : productosAMostrar) {
                                System.out.println(prod);
                            }
                        }
                        break;
                        
                    case 2:
                        System.out.print("Nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Descripcion: ");
                        String desc = scanner.nextLine();
                        double precio = Validador.leerDouble(scanner, "Precio: ");
                        int stock = Validador.leerEntero(scanner, "Stock inicial: ");
                        System.out.print("Ruta de la imagen: ");
                        String img = scanner.nextLine();
                        
                        long catId = Validador.leerEntero(scanner, "ID de la Categoria asociada: ");
                        Categoria cat = categoriaService.buscarPorId(catId);
                        
                        Producto nuevo = new Producto(nombre, precio, desc, stock, img, cat, false, LocalDateTime.now());
                        productoService.crearProducto(nuevo);
                        cat.agregarProducto(nuevo);
                        System.out.println("Producto creado con exito. ID: " + nuevo.getId());
                        break;
                        
                    case 3:
                        long id = Validador.leerEntero(scanner, "ID del producto a editar: ");
                        Producto p = productoService.buscarPorId(id);

                        System.out.print("Nuevo Nombre (Actual: " + p.getNombre() + " - Presione Enter para mantener): "); 
                        String nom = scanner.nextLine();
                        String nomFinal = nom.trim().isEmpty() ? p.getNombre() : nom;

                        // Lectura opcional de Precio
                        double precioFinal = p.getPrecio();
                        while (true) {
                            System.out.print("Nuevo Precio (Actual: $" + p.getPrecio() + " - Presione Enter para mantener): ");
                            String precioInput = scanner.nextLine();
                            if (precioInput.trim().isEmpty()) {
                                break; // mantiene el actual
                            }
                            try {
                                double pr = Double.parseDouble(precioInput);
                                if (pr < 0) {
                                    System.out.println("Error: El precio no puede ser negativo.");
                                    continue;
                               }
                                precioFinal = pr;
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Ingrese un numero decimal valido.");
                            }
                        }

                        // Lectura opcional de Stock
                        int stockFinal = p.getStock();
                        while (true) {
                            System.out.print("Nuevo Stock (Actual: " + p.getStock() + " - Presione Enter para mantener): ");
                            String stockInput = scanner.nextLine();
                            if (stockInput.trim().isEmpty()) {
                                break; // mantiene el actual
                            }
                            try {
                                int st = Integer.parseInt(stockInput);
                                if (st < 0) {
                                    System.out.println("Error: El stock no puede ser negativo.");
                                    continue;
                                }
                                stockFinal = st;
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Ingrese un numero entero valido.");
                            }
                        }

                        System.out.print("Nueva Imagen (Actual: " + p.getImagen() + " - Presione Enter para mantener): "); 
                        String nuevaImg = scanner.nextLine();
                        String imgFinal = nuevaImg.trim().isEmpty() ? p.getImagen() : nuevaImg;
                        
                        productoService.editarProducto(id, nomFinal, precioFinal, stockFinal, imgFinal);
                        System.out.println("Producto modificado correctamente.");
                        break;
                        
                    case 4:
                        long idEliminar = Validador.leerEntero(scanner, "ID del producto a eliminar: ");
                        System.out.print("¿Confirmar baja? (S/N): ");
                        if (scanner.nextLine().equalsIgnoreCase("S")) {
                            productoService.eliminarProducto(idEliminar);
                            System.out.println("Producto retirado.");
                        }
                        break;
                }
            } catch (NegocioException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // ====================
    // 3. SUBMENU USUARIOS
    // ====================
    private static void menuUsuarios(Scanner scanner) {
        while (true) {
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("0. Volver al Menu Principal");
            
            int opcion = Validador.leerEntero(scanner, "Seleccione una opcion: ");
            if (opcion == 0) {
                break;
            }

            try {
                Validador.validarOpcionMenu(opcion, 1, 4);
                switch (opcion) {
                    case 1:
                        ArrayList<Usuario> activos = usuarioService.obtenerUsuariosActivos();
                        if (activos.isEmpty()) {
                            System.out.println("No hay usuarios registrados.");
                        } else {
                            for (Usuario user : activos) {
                                System.out.println(user);
                            }
                        }
                        break;
                        
                    case 2:
                        System.out.print("Nombre: "); 
                        String nom = scanner.nextLine();
                        System.out.print("Apellido: "); 
                        String ape = scanner.nextLine();
                        System.out.print("Email: "); 
                        String mail = scanner.nextLine();
                        System.out.print("Celular: "); 
                        String cel = scanner.nextLine();
                        System.out.print("Contraseña: "); 
                        String pass = scanner.nextLine();
                        
                        System.out.println("Seleccione Rol (1. ADMIN / 2. USUARIO): ");
                        int rolSel = Validador.leerEntero(scanner, "-> ");
                        Rol rol = Rol.USUARIO;
                        if (rolSel == 1) {
                            rol = Rol.ADMIN;
                        }
                        
                        Usuario nuevo = new Usuario(nom, ape, mail, cel, pass, rol, new ArrayList<>(), false, LocalDateTime.now());
                        usuarioService.crearUsuario(nuevo);
                        System.out.println("Usuario guardado con éxito. ID: " + nuevo.getId());
                        break;
                        
                    case 3:
                        long id = Validador.leerEntero(scanner, "ID del usuario a editar: ");
                        Usuario u = usuarioService.buscarPorId(id);

                        System.out.print("Nuevo Nombre (Actual: " + u.getNombre() + " - Presione Enter para mantener): "); 
                        String nuevoNom = scanner.nextLine();
                        System.out.print("Nuevo Apellido (Actual: " + u.getApellido() + " - Presione Enter para mantener): "); 
                        String nuevoApe = scanner.nextLine();
                        System.out.print("Nuevo Email (Actual: " + u.getMail() + " - Presione Enter para mantener): "); 
                        String nuevoMail = scanner.nextLine();
                        System.out.print("Nuevo Celular (Actual: " + u.getCelular() + " - Presione Enter para mantener): "); 
                        String nuevoCel = scanner.nextLine();
                        
                        // Si viene vacío, se autocompeta con el valor previo
                        String nomFinal = nuevoNom.trim().isEmpty() ? u.getNombre() : nuevoNom;
                        String apeFinal = nuevoApe.trim().isEmpty() ? u.getApellido() : nuevoApe;
                        String mailFinal = nuevoMail.trim().isEmpty() ? u.getMail() : nuevoMail;
                        String celFinal = nuevoCel.trim().isEmpty() ? u.getCelular() : nuevoCel;

                        usuarioService.editarUsuario(id, nomFinal, apeFinal, mailFinal, celFinal);
                        System.out.println("Datos del usuario actualizados.");
                        break;
                        
                    case 4:
                        long idEliminar = Validador.leerEntero(scanner, "ID del usuario a eliminar: ");
                        System.out.print("¿Confirmar eliminación logica? (S/N): ");
                        if (scanner.nextLine().equalsIgnoreCase("S")) {
                            usuarioService.eliminarUsuario(idEliminar);
                            System.out.println("Usuario inhabilitado para futuros pedidos.");
                        }
                        break;
                }
            } catch (NegocioException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // ==============================
    // 4. SUBMENU PEDIDOS Y DETALLES
    // ==============================
    private static void menuPedidos(Scanner scanner) {
        while (true) {
            System.out.println("\n--- GESTION DE PEDIDOS ---");
            System.out.println("1. Listar pedidos");
            System.out.println("2. Registrar nuevo pedido (con detalles)");
            System.out.println("3. Actualizar estado y forma de pago");
            System.out.println("4. Cancelar pedido (Baja logica)");
            System.out.println("0. Volver al Menu Principal");
            
            int opcion = Validador.leerEntero(scanner, "Seleccione una opcion: ");
            if (opcion == 0) {
                break;
            }

            try {
                Validador.validarOpcionMenu(opcion, 1, 4);
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- OPCIONES DE LISTADO ---");
                        System.out.println("1. Listar todos los pedidos");
                        System.out.println("2. Filtrar pedidos por usuario");
                        int tipoListadoPedido = Validador.leerEntero(scanner, "Seleccione una opcion: ");

                        ArrayList<Pedido> pedidosAMostrar;
                        if (tipoListadoPedido == 2) {
                            long usrId = Validador.leerEntero(scanner, "Ingrese ID del usuario: ");
                            // validar si el usuario existe2
                            usuarioService.buscarPorId(usrId); 
                            pedidosAMostrar = pedidoService.obtenerPedidosPorUsuario(usrId);
                        } else {
                            pedidosAMostrar = pedidoService.obtenerPedidosActivos();
                        }

                        if (pedidosAMostrar.isEmpty()) {
                            System.out.println("No se registran pedidos para mostrar.");
                        } else {
                            for (Pedido p : pedidosAMostrar) {
                                System.out.println(p);
                                for (DetallePedido d : p.getDetalles()) {
                                    System.out.println("   " + d);
                                }
                            }
                        }
                        break;
                        
                    case 2:
                        long userId = Validador.leerEntero(scanner, "ID del Usuario que realiza la compra: ");
                        Usuario user = usuarioService.buscarPorId(userId);
                        
                        System.out.println("Seleccione Forma de Pago (1. TARJETA / 2. TRANSFERENCIA / 3. EFECTIVO): ");
                        int formaPagoSelect = Validador.leerEntero(scanner, "-> ");
                        FormaPago fp = FormaPago.EFECTIVO;
                        if (formaPagoSelect == 1) {
                            fp = FormaPago.TARJETA;
                        } else if (formaPagoSelect == 2) {
                            fp = FormaPago.TRANSFERENCIA;
                        }

                        Pedido nuevoPedido = new Pedido(java.time.LocalDate.now(), Estado.PENDIENTE, fp, user, false, LocalDateTime.now());
                        
                        // carga manual de detalles
                        while (true) {
                            System.out.println("\n--- Agregando item al Pedido ---");
                            long productId = Validador.leerEntero(scanner, "ID del Producto: ");
                            Producto product = productoService.buscarPorId(productId);
                            int cant = Validador.leerEntero(scanner, "Cantidad: ");
                            
                            nuevoPedido.addDetallePedido(cant, product);
                            
                            System.out.print("¿Desea agregar otro producto? (S/N): ");
                            String continuar = scanner.nextLine();
                            if (!continuar.equalsIgnoreCase("S")) {
                                break;
                            }
                        }
                        
                        pedidoService.registrarPedido(nuevoPedido);
                        user.agregarPedido(nuevoPedido);
                        System.out.println("Pedido registrado con exito. ID: " + nuevoPedido.getId() + " | Total: $" + nuevoPedido.getTotal());
                        break;
                        
                    case 3:
                        long id = Validador.leerEntero(scanner, "ID del Pedido a modificar: ");
                        
                        System.out.println("Nuevo Estado (1. PENDIENTE / 2. CONFIRMADO / 3. TERMINADO / 4. CANCELADO): ");
                        int estadoSelect = Validador.leerEntero(scanner, "-> ");
                        Estado est = Estado.CANCELADO;
                        if (estadoSelect == 1) {
                            est = Estado.PENDIENTE;
                        } else if (estadoSelect == 2) {
                            est = Estado.CONFIRMADO;
                        } else if (estadoSelect == 3) {
                            est = Estado.TERMINADO;
                        }
                        
                        System.out.println("Nueva Forma de Pago (1. TARJETA / 2. TRANSFERENCIA / 3. EFECTIVO): ");
                        int formaPagoModSelect = Validador.leerEntero(scanner, "-> ");
                        FormaPago formaPagoMod = FormaPago.EFECTIVO;
                        if (formaPagoModSelect == 1) {
                            formaPagoMod = FormaPago.TARJETA;
                        } else if (formaPagoModSelect == 2) {
                            formaPagoMod = FormaPago.TRANSFERENCIA;
                        }
                        
                        pedidoService.actualizarEstadoYPago(id, est, formaPagoMod);
                        System.out.println("Pedido actualizado.");
                        break;
                        
                    case 4:
                        long idCancelar = Validador.leerEntero(scanner, "ID del Pedido a cancelar: ");
                        System.out.print("¿Seguro que desea anular el pedido? (S/N): ");
                        if (scanner.nextLine().equalsIgnoreCase("S")) {
                            pedidoService.eliminarPedido(idCancelar);
                            System.out.println("Pedido cancelado lógicamente.");
                        }
                        break;
                }
            } catch (NegocioException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}