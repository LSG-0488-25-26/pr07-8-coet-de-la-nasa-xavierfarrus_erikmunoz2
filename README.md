# Yu-Gi-Oh! Card Collection App

## 📱 Descripción del Proyecto

Aplicación Android desarrollada con Jetpack Compose que permite consultar, coleccionar y gestionar cartas de Yu-Gi-Oh! mediante una API REST. La aplicación incluye funcionalidades de apertura de sobres, gestión de inventario y sistema de favoritos con persistencia en base de datos SQLite.

## ✨ Características Principales

### 🎴 Gestión de Cartas
- **Consulta de API REST**: Obtención de información de cartas desde la API de YGOPro Deck
- **Visualización Responsive**: Diseño adaptativo para Compact, Medium y Expanded
- **Búsqueda**: SearchBar para filtrar cartas por nombre
- **Detalle de Carta**: Pantalla con información completa de cada carta

### 💎 Sistema de Sobres
- **Apertura de Sobres**: Generación aleatoria de 5 cartas
- **Guardado en Inventario**: Almacena cartas nuevas, evita duplicados
- **Feedback Visual**: Notificaciones de cartas nuevas vs repetidas

### ⭐ Sistema de Favoritos (NUEVO)
- **Marcar Favoritos**: Botón flotante (FAB) en pantalla de detalle
- **Persistencia**: Favoritos guardados en base de datos SQLite con Room
- **Vista de Favoritos**: Pantalla dedicada para ver todas las cartas favoritas
- **Indicadores Visuales**: Icono de corazón rojo en cartas favoritas

### 📦 Inventario Persistente (NUEVO)
- **Almacenamiento Local**: Room database para guardar el inventario
- **Carga Automática**: El inventario se restaura al abrir la app
- **Sin Duplicados**: Solo guarda cartas únicas
- **Estadísticas**: Contador de cartas en inventario

## 🏗️ Arquitectura y Tecnologías

### Patrón MVVM
```
View (Composables) ↔️ ViewModel (LiveData) ↔️ Repository ↔️ Data Sources
                                                           ├── Retrofit (API)
                                                           └── Room (DB Local)
```

### Stack Tecnológico
- **UI**: Jetpack Compose + Material 3
- **Navegación**: Navigation Compose
- **Persistencia**: Room Database + SQLite
- **Red**: Retrofit + Gson
- **Imágenes**: Glide Compose
- **Asincronía**: Kotlin Coroutines
- **Arquitectura**: MVVM + LiveData
- **Responsive**: WindowSizeClass API

## 📊 Requisitos Cumplidos

| Requisito | Estado |
|-----------|--------|
| Lazy Composables | ✅ |
| Responsive (Media Queries) | ✅ |
| LiveData | ✅ |
| MVVM Pattern | ✅ |
| Navigation Routes | ✅ |
| Retrofit API REST | ✅ |
| Room + SQLite | ✅ |
| SearchBar | ✅ |
| Scaffold TopBar/BottomBar | ✅ |

## 📂 Estructura del Proyecto

```
app/src/main/java/com/example/yugioh/
├── api/
│   ├── ApiService.kt
│   └── Repository.kt
├── model/
│   ├── YugiohCard.kt
│   ├── CardImage.kt
│   ├── CardPrice.kt
│   ├── CardSet.kt
│   └── BanlistInfo.kt
├── room/
│   ├── YugiohDatabase.kt
│   ├── YugiohApplication.kt
│   ├── RoomRepository.kt
│   ├── Converters.kt
│   ├── entity/
│   │   ├── FavoriteCard.kt
│   │   └── InventoryCard.kt
│   └── dao/
│       ├── FavoriteCardDao.kt
│       └── InventoryCardDao.kt
├── viewmodel/
│   ├── CardsViewModel.kt
│   ├── InventoryViewModel.kt
│   ├── FavoritesViewModel.kt
│   ├── ScaffoldViewModel.kt
│   └── SearchBarViewModel.kt
├── view/
│   ├── HomeScreen.kt
│   ├── DetailScreen.kt
│   ├── InventoryScreen.kt
│   ├── FavoriteScreen.kt
│   ├── OpenScreen.kt
│   ├── SearchScreen.kt
│   ├── CardItem.kt
│   ├── MyTopAppBar.kt
│   ├── MyBottomBar.kt
│   └── MyAppNavHost.kt
├── navigation/
│   └── Routes.kt
└── MainActivity.kt
```

## 🚀 Instalación y Uso

### Requisitos
- Android Studio Hedgehog o superior
- Kotlin 1.9+
- Gradle 8.0+
- MinSDK: 26 (Android 8.0)
- TargetSDK: 36

### Pasos
1. Clona el repositorio
2. Abre el proyecto en Android Studio
3. Sincroniza Gradle
4. Ejecuta la app en un emulador o dispositivo físico

### Primera Ejecución
- La app cargará automáticamente 400 cartas desde la API
- La base de datos SQLite se creará automáticamente
- Puedes empezar a abrir sobres y marcar favoritos

## 📸 Capturas de Pantalla

*(Añade aquí capturas del simulador con el resultado final de la app)*

### Pantallas Principales
- 🏠 **Home**: Lista/Grid de cartas con búsqueda
- 🎴 **Detalle**: Información completa + botón de favoritos
- ⭐ **Favoritos**: Colección de cartas favoritas
- 📦 **Abrir Sobres**: Generación y guardado de cartas
- 🗂️ **Inventario**: Cartas guardadas con persistencia

## 🎨 Diseño Responsive

### Compact (Teléfonos)
- Lista vertical con CardItems
- 1 columna

### Medium (Tablets pequeñas)
- Grid de 2 columnas
- Espaciado optimizado

### Expanded (Tablets grandes, pantallas horizontales)
- Grid de 3 columnas
- Uso aprovechado del espacio

## 🛠️ Funcionalidades de Room

### Base de Datos
- **Nombre**: `yugioh_database`
- **Tablas**: `favorite_cards`, `inventory_cards`
- **Versión**: 1

### Operaciones Disponibles
```kotlin
// Favoritos
- getAllFavorites(): List<YugiohCard>
- isFavorite(cardId: Int): Boolean
- addFavorite(card: YugiohCard)
- removeFavorite(cardId: Int)
- getFavoritesCount(): Int

// Inventario
- getAllInventoryCards(): List<YugiohCard>
- isInInventory(cardId: Int): Boolean
- addToInventory(card: YugiohCard)
- addMultipleToInventory(cards: List<YugiohCard>)
- removeFromInventory(cardId: Int)
- getInventoryCount(): Int
```

## 🐛 Solución de Problemas

### Pantalla negra al iniciar
- Asegúrate de tener conexión a Internet para cargar las cartas
- Espera 15 segundos (hay un timeout configurado)
- Si persiste, toca "Reintentar"

### Las cartas no se guardan
- Verifica que la app tenga permisos de almacenamiento
- Revisa que Room esté correctamente configurado en build.gradle

### Advertencias de compilación
- La advertencia de `SearchBar` es conocida y no afecta la funcionalidad
- `ArrowBack` ha sido actualizado a la versión AutoMirrored

## 👥 Autores

- Xavier Farrus
- Erik Muñoz

## 📚 Referencias

- [API Yu-Gi-Oh! YGOPro Deck](https://ygoprodeck.com/api-guide/)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Material Design 3](https://m3.material.io/)

## 📄 Licencia

Proyecto educativo - DAM2

---

**Fecha de entrega**: Febrero 2026  
**Repositorio**: GitHub Classroom - PR07-8  
**Documentación técnica**: Ver `ROOM_IMPLEMENTATION.md` para detalles de implementación
