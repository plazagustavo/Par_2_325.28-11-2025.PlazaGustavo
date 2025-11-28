# Portal Cinemas - Sistema de Reserva de Entradas

Una aplicación de escritorio desarrollada con **JavaFX** para gestionar la reserva y compra de entradas de cine de forma intuitiva y segura.

## 📋 Descripción General

**Portal Cinemas** es un sistema completo de gestión de entradas cinematográficas que permite a los usuarios:
- Registrarse e iniciar sesión de forma segura
- Explorar salas disponibles con películas actuales
- Seleccionar butacas específicas en tiempo real
- Confirmar y procesar compras de entradas
- Persistencia de datos automática

La aplicación utiliza una arquitectura **Modelo-Vista-Controlador (MVC)** con interfaz gráfica basada en **FXML** y almacenamiento de datos mediante serialización.

## 🎯 Características Principales

### Autenticación de Usuarios
- Sistema de login y registro seguro
- Validación de email y contraseña
- Gestión de múltiples clientes simultáneamente

### Gestión de Salas y Películas
- 6 salas de cine con películas diferentes
- Películas incluidas: Avatar 3, Oppenheimer, Barbie, Dune 2, Insidious, Cars
- Visualización en tiempo real de butacas disponibles

### Selección de Butacas
- Interfaz visual para seleccionar asientos específicos
- Indicadores de disponibilidad de butacas
- Confirmación de compra antes de procesar

### Persistencia de Datos
- Guardado automático de datos al cerrar la aplicación
- Carga automática de datos al iniciar
- Serialización Java para almacenamiento seguro

##  Estructura del Proyecto

### Carpetas Principales

\`\`\`
src/cine/
├── Main.java                          # Punto de entrada de la aplicación
├── modelo/                            # Clases del modelo de datos
│   ├── Cine.java                      # Gestiona cines, salas y clientes
│   ├── Cliente.java                   # Información del usuario
│   ├── Sala.java                      # Información de la sala y película
│   ├── Butaca.java                    # Información de asientos
│   └── Entrada.java                   # Registro de entradas compradas
├── controlador/                       # Controladores MVC
│   ├── ViewLoginController.java       # Gestiona login y registro
│   ├── ViewPrincipalController.java   # Menú principal
│   ├── ViewButacasController.java     # Selección de butacas
│   └── ViewConfirmacionController.java# Confirmación de compra
├── vista/                             # Archivos FXML
│   ├── ViewLogin.fxml                 # Interfaz de login
│   ├── ViewPrincipal.fxml             # Menú principal
│   ├── ViewButacas.fxml               # Selección de butacas
│   └── ViewConfirmacion.fxml          # Confirmación de compra
├── persistencia/                      # Gestión de datos persistentes
│   └── PersistenciaDatos.java         # Serialización de datos
└── images/                            # Recursos de imagen
    └── login.jpg                      # Imagen de fondo del login
\`\`\`

## 📦 Requisitos Técnicos

### Dependencias
- **Java JDK 11+**
- **JavaFX con Scene Builder**** (para interfaz gráfica)


## 🚀 Instalación y Ejecución

### Opción 1: Usando NetBeans

1. **Clonar o descargar el proyecto**
   \`\`\`bash
   git clone https://github.com/plazagustavo/Cine-App.git
   cd Cine-App
   \`\`\`

2. **Abrir el proyecto en NetBeans**
   - File → Open Project
   - Seleccionar la carpeta del proyecto

3. **Configurar JavaFX (si no está configurado)**
   - Click derecho en el proyecto → Properties
   - Libraries → Agregar la librería JavaFX

4. **Ejecutar la aplicación**
   - Click derecho en el proyecto → Run
   - O presionar F6


## 📚 Flujo de la Aplicación

\`\`\`
Inicio (Main.java)
    ↓
Cargar datos guardados (PersistenciaDatos)
    ↓
[ViewLogin] - Login/Registro
    ├─ Registrarse: Nuevo usuario
    └─ Iniciar sesión: Usuario existente
    ↓
[ViewPrincipal] - Seleccionar película/sala
    ├─ Ver lista de salas disponibles
    └─ Seleccionar sala para comprar entrada
    ↓
[ViewButacas] - Seleccionar asientos
    ├─ Ver butacas disponibles
    └─ Seleccionar butaca(s)
    ↓
[ViewConfirmacion] - Confirmar compra
    ├─ Revisar detalles
    └─ Confirmar o cancelar
    ↓
Guardar datos (PersistenciaDatos)
\`\`\`


## 🎨 Interfaz Gráfica

La interfaz está diseñada con FXML (XML para JavaFX) y CSS para mejorar la experiencia del usuario:

- **ViewLogin.fxml**: Pantalla de acceso con fondo personalizado
- <img width="852" height="531" alt="image" src="https://github.com/user-attachments/assets/8e558eb1-f454-48ad-a92b-85a31aa62188" />
- **ViewPrincipal.fxml**: Menú principal con lista de películas
- <img width="799" height="627" alt="image" src="https://github.com/user-attachments/assets/1092b2cf-6fc1-4f48-9927-3d01ca03de8d" />
- **ViewButacas.fxml**: Visualización de butacas con selección interactiva
- <img width="799" height="623" alt="image" src="https://github.com/user-attachments/assets/97958b81-6aad-4a56-a478-c99b2c25c1c3" />
- **ViewConfirmacion.fxml**: Resumen de compra
- <img width="498" height="429" alt="image" src="https://github.com/user-attachments/assets/751aa37a-f77f-4eed-a9d0-906b43e9fc3d" />


## 📝 Datos de Prueba

La aplicación inicia con 6 salas de cine:

| Sala | Película |
|------|----------|
| 1 | Avatar 3 |
| 2 | Oppenheimer |
| 3 | Barbie |
| 4 | Dune 2 |
| 5 | Insidious |
| 6 | Cars |




## 📄 Licencia

Este proyecto es de uso libre para fines educativos. Si lo usás o modificás, ¡agradezco que des créditos!
