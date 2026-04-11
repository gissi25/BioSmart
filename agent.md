# Documentación del Agente - BioSmart

## 1. Información General del Proyecto

**Nombre:** BioSmart  
**Tipo:** Aplicación Android Nativa  
**Paquete base:** `sv.ues.fia.eisi.biosmart`  
**Versión actual:** 1.0  
**SDK Mínimo:** API 24 (Android 7.0)  
**SDK Objetivo:** API 36  

## 2. Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/sv/ues/fia/eisi/biosmart/
│   │   ├── data/
│   │   │   ├── model/       # Modelos de datos (Usuario, Chequeo, Dispositivo)
│   │   │   └── repository/  # Repositorios (AuthRepository, SaludRepository)
│   │   ├── ui/
│   │   │   ├── login/       # Autenticación
│   │   │   ├── dashboard/   # Panel principal
│   │   │   ├── facial/       # Reconocimiento facial
│   │   │   ├── huella/       # Huella dactilar
│   │   │   ├── correo/       # Login por correo
│   │   │   ├── medicion/     # Temperatura y ritmo cardíaco
│   │   │   ├── historial/   # Historial de chequeos
│   │   │   ├── reporte/      # Generación de reportes PDF
│   │   │   └── registro/    # Registro de usuarios
│   │   └── utils/           # Utilidades (TTS, PDF, Bluetooth)
│   └── res/
│       ├── layout/           # Layouts XML
│       ├── navigation/       # nav_graph.xml
│       ├── menu/            # Menús de navegación
│       ├── values/          # Recursos (strings, colors, themes)
│       └── drawable/        # Imágenes y vectores
```

## 3. Tecnologías y Dependencias

- **Lenguaje:** Kotlin 1.9.x
- **UI:** ViewBinding + XML
- **Navegación:** Android Navigation Component
- **Arquitectura:** MVVM (Model-View-ViewModel)
- **Firebase:**-Comentarizado (pendiente de implementación)
- **Build System:** Gradle 9.3.1 con Kotlin DSL

### Dependencias principales:
- androidx.core.ktx
- androidx.appcompat
- Material Design Components
- AndroidX Activity
- AndroidX ConstraintLayout
- AndroidX Navigation (Fragment + UI)

## 4. Estado de Implementación

### Completado:
- Estructura base del proyecto
- Sistema de navegación
- Pantalla de Login con 3 opciones de autenticación
- Fragmentos de autenticación (facial, huella, correo) - UI básica
- Pantalla de Dashboard
- Layouts para todas las funciones

### Pendiente/Incompleto:
- Implementación de reconocimiento facial real
- Implementación de huella dactilar
- Conexión con Firebase
- Lógica de autenticación completa
- Mediciones de temperatura y ritmo cardíaco
- Generación de reportes PDF
- Historial de chequeos
- Registro de usuarios

## 5. Configuración del Entorno

Para compilar el proyecto:
```bash
./gradlew assembleDebug  # Linux/Mac
gradlew.bat assembleDebug  # Windows
```

Requiere:
- Android Studio Arctic Fox o superior
- JDK 11 o superior
- Conexión a internet para descargar dependencias

## 6. Notas para el Desarrollo

1. **ViewBinding:** Todos los layouts usan ViewBinding. Cada fragmento debe inflar su binding correspondiente.

2. **Navegación:** Usar `findNavController()` para navegar entre fragmentos. Las acciones están definidas en `nav_graph.xml`.

3. **Modelos de datos:** 
   - `Usuario`: id, nombre, correo, edad, tipoSangre, pesoReferencia, tieneHuella
   - `Chequeo`: datos de mediciones de salud
   - `Dispositivo`: información de dispositivos Bluetooth

4. **Permisos faltantes:** El AndroidManifest.xml necesita permisos para:
   - Cámara (reconocimiento facial)
   - Biométricos
   - Bluetooth
   - Internet (Firebase)

5. **Recursos:** 
   - Colores en `values/colors.xml`
   - Strings en `values/strings.xml`
   - Temas en `values/themes.xml`

## 7. Recomendaciones

- Implementar Firebase Authentication para login
- Agregar Firebase Firestore para persistencia de datos
- Implementar biblioteca ML Kit para reconocimiento facial
- Completar la lógica de medición de signos vitales
- Agregar tests unitarios y de instrumentación
- Implementar manejo de errores y excepciones
- Agregar validación de formularios