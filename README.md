# Agenda_Contactos
Aplicación de consola en **Kotlin** para gestionar una agenda básica de contactos almacenados en memoria mediante una `MutableList`.

## Objetivo

Comprender cómo almacenar varios datos en memoria y manipularlos con una lista dinámica, trabajando con:

- Colecciones mutables (`mutableListOf`)
- Entrada de datos por consola
- Menús interactivos
- Búsqueda y eliminación de elementos
- Validación de datos

## Funcionalidades

```
===== AGENDA DE CONTACTOS =====
1. Añadir contacto
2. Listar contactos
3. Buscar contacto
4. Eliminar contacto
5. Salir
```

| Opción | Descripción |
|--------|-------------|
| **1. Añadir contacto** | Solicita nombre, teléfono y correo electrónico, y añade el contacto a la agenda. |
| **2. Listar contactos** | Muestra los contactos numerados y ordenados alfabéticamente por nombre. |
| **3. Buscar contacto** | Busca por nombre con coincidencia total o parcial, sin distinguir mayúsculas de minúsculas. |
| **4. Eliminar contacto** | Muestra la lista y elimina el contacto según su número de posición. |
| **5. Salir** | Finaliza el programa. |

El menú se repite hasta que el usuario selecciona la opción de salir.

## Requisitos

- IntelliJ IDEA
- JDK 17 o superior
- Kotlin 

## Estructura del proyecto

```
UD01_Actividad07_AgendaContactos/
├── src/
│   └── main/
│       └── kotlin/
│           └── Main.kt
└── README.md
```
