# BioSmart - Aplicación de Salud Biométrica

## Descripción del Proyecto

BioSmart es una aplicación Android de monitoreo de salud que integra tecnologías de autenticación biométrica para proporcionar un acceso seguro y personalizado a funcionalidades de salud. El proyecto está desarrollado en Kotlin utilizando Android Jetpack y sigue una arquitectura MVVM.

## Características Principales

- **Autenticación Biométrica:** Tres métodos de inicio de sesión:
  - Reconocimiento facial
  - Huella dactilar
  - Correo electrónico
  
- **Monitoreo de Salud:**
  - Medición de temperatura corporal
  - Monitoreo de ritmo cardíaco
  - Registro de historial de chequeos

- **Gestión de Usuarios:**
  - Registro de nuevos usuarios
  - Almacenamiento de datos de salud
  - Historial de mediciones

- **Reportes:**
  - Generación de reportes en PDF
  - Envío de reportes por correo electrónico

## Estructura del Proyecto

```
app/src/main/java/sv/ues/fia/eisi/biosmart/
├── BioSmartApp.kt              # Clase Application
├── data/
│   ├── model/
│   │   ├── Usuario.kt          # Modelo de usuario
│   │   ├── Chequeo.kt          # Modelo de chequeo médico
│   │   └── Dispositivo.kt      # Modelo de dispositivo Bluetooth
│   └── repository/
│       ├── AuthRepository.kt    # Repositorio de autenticación
│       └── SaludRepository.kt   # Repositorio de datos de salud
├── ui/
│   ├── main/
│   │   └── MainActivity.kt     # Activity principal
│   ├── login/
│   │   ├── LoginFragment.kt    # Pantalla de login
│   │   └── LoginViewModel.kt   # ViewModel de login
│   ├── dashboard/
│   │   └── DashboardFragment.kt
│   ├── facial/
│   │   └── FacialFragment.kt
│   ├── huella/
│   │   └── HuellaFragment.kt
│   ├── correo/
│   │   └── CorreoFragment.kt
│   ├── medicion/
│   │   ├── TemperaturaFragment.kt
│   │   └── RitmoFragment.kt
│   ├── historial/
│   │   ├── HistorialFragment.kt
│   │   └── HistorialAdapter.kt
│   ├── reporte/
│   │   └── ReporteFragment.kt
│   └── registro/
│       └── RegistroFragment.kt
└── utils/
    ├── TtsHelper.kt            # Text-to-Speech
    ├── PdfGenerator.kt        # Generación de PDF
    └── BlutoothHelper.kt      # Conexión Bluetooth
```

## Requisitos del Sistema

- **Android SDK:** API 24 (Android 7.0) o superior
- **JDK:** 11 o superior
- **Android Studio:** Arctic Fox o superior

## Configuración del Proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   ```

2. **Abrir en Android Studio:**
   - File > Open > Seleccionar la carpeta del proyecto
   - Esperar a que se sincronice Gradle

3. **Compilar el proyecto:**
   ```bash
   # En Windows
   gradlew.bat assembleDebug
   
   # En Linux/Mac
   ./gradlew assembleDebug
   ```

4. **Ejecutar en dispositivo:**
   - Conectar un dispositivo Android con depuración USB
   - Presionar Run en Android Studio

## Tecnologías Utilizadas

| Categoría | Tecnología |
|-----------|------------|
| Lenguaje | Kotlin |
| UI | ViewBinding + XML |
| Navegación | Android Navigation Component |
| Arquitectura | MVVM |
| Build System | Gradle 9.3.1 (Kotlin DSL) |
| Dependencias | AndroidX, Material Design |

## Estado del Proyecto

El proyecto se encuentra en **desarrollo inicial**. La estructura base y la interfaz de usuario están implementadas, pero algunas funcionalidades están pendientes de implementación completa:

- [ ] Autenticación con Firebase (comentarizado)
- [ ] Reconocimiento facial con ML Kit
- [ ] Lógica de huella dactilar
- [ ] Mediciones de signos vitales
- [ ] Generación de reportes PDF
- [ ] Historial de chequeos

## Permisos Requeridos

La aplicación requiere los siguientes permisos (a agregar en AndroidManifest.xml):
- `android.permission.CAMERA` - Para reconocimiento facial
- `android.permission.USE_BIOMETRIC` - Para huella dactilar
- `android.permission.BLUETOOTH` - Para conectar dispositivos de salud
- `android.permission.INTERNET` - Para Firebase
- `android.permission.ACCESS_NETWORK_STATE`

## Contribuciones

Este proyecto es desarrollado como parte de un trabajo académico. Las contribuciones son bienvenidas siguiendo las directrices del proyecto.

## Licencia

Proyecto académico - Universidad de El Salvador (UES)

## Contacto

Para preguntas o soporte, contactar al equipo de desarrollo.