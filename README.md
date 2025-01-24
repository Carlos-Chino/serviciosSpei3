# Automatización de servicios de spei 3 consumidos 

Este proyecto incluye la implementación de varios servicios en Java, diseñados para consumir APIs mediante diferentes métodos HTTP como GET, POST y PUT. A continuación, se describen los servicios disponibles y cómo utilizarlos.

--------------------------------------

## **Tabla de Contenido**
- [Requisitos Previos](#requisitos-previos)
- [Servicios Disponibles](#servicios-disponibles)
- [Archivo properties](#archivo-de-propiedades)
- [Main](#Main)

--------------------------------

## **Requisitos Previos**
Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes componentes:
- **Java en su versión 17 o superior**.
- **Maven** para gestionar las dependencias.
- Insumos correctos en **propiedades.properties**.

-------------------------------

# **Servicios Disponibles**
- Envío de órdenes de tipos: `1`, `7`, `19`,`20`, `21`, `22`.
- Devoluciones de órdenes: `0`, `16`, `17`,`23`, `24`.
- Consulta de órdenes envías y recibidas.
- Consulta de órdenes enviadas y recibidas históricas.
- Consulta de órdenes pendientes enviadas y recibidas.
- Search de órdenes enviadas y recibidas.
- Search de órdenes enviadas y recibidas históricas.
- Consulta de UrlCep para órdenes enviadas y recibidas.

-------------------------------

# **Archivo de propiedades**

- Configurar los datos generales y del ordenante que se encuentra en la sección: `#Pagos`
- Configurar del beneficiario para operaciones exitosas que se encuentra en la sección: `#PagosValidos`
- Configurar del beneficiario para operaciones inválidas que se encuentra en la sección: `#PagosInvalidos`
- `Para la consulta de órdenes por parámetros:`
- Configurar las secciones: `Parámetros`, `Params` y `Parámetros pendientes`

-----------------

# **Main**
- Para ejecutar cualquier servicio solo es necesario descomentar el servicio a ejecutar.