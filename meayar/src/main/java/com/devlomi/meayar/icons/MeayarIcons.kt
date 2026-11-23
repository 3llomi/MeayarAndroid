package com.devlomi.meayar.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * MEAYAR Official Iconography.
 * Built directly as Compose ImageVectors with standard 24x24dp viewport.
 */
object MeayarIcons {

    val ArrowForward: ImageVector by lazy {
        ImageVector.Builder(
            name = "ArrowForward",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                strokeLineWidth = 0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 4f)
                lineTo(10.59f, 5.41f)
                lineTo(16.17f, 11f)
                horizontalLineTo(4f)
                verticalLineTo(13f)
                horizontalLineTo(16.17f)
                lineTo(10.59f, 18.59f)
                lineTo(12f, 20f)
                lineTo(20f, 12f)
                close()
            }
        }.build()
    }

    val ArrowBack: ImageVector by lazy {
        ImageVector.Builder(
            name = "ArrowBack",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                strokeLineWidth = 0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20f, 11f)
                horizontalLineTo(7.83f)
                lineTo(13.42f, 5.41f)
                lineTo(12f, 4f)
                lineTo(4f, 12f)
                lineTo(12f, 20f)
                lineTo(13.41f, 18.59f)
                lineTo(7.83f, 13f)
                horizontalLineTo(20f)
                verticalLineTo(11f)
                close()
            }
        }.build()
    }

    val ChevronRight: ImageVector by lazy {
        ImageVector.Builder(
            name = "ChevronRight",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                strokeLineWidth = 0f
            ) {
                moveTo(10f, 6f)
                lineTo(8.59f, 7.41f)
                lineTo(13.17f, 12f)
                lineTo(8.59f, 16.59f)
                lineTo(10f, 18f)
                lineTo(16f, 12f)
                close()
            }
        }.build()
    }

    val ChevronLeft: ImageVector by lazy {
        ImageVector.Builder(
            name = "ChevronLeft",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                strokeLineWidth = 0f
            ) {
                moveTo(15.41f, 7.41f)
                lineTo(14f, 6f)
                lineTo(8f, 12f)
                lineTo(14f, 18f)
                lineTo(15.41f, 16.59f)
                lineTo(10.83f, 12f)
                close()
            }
        }.build()
    }

    val ChevronDown: ImageVector by lazy {
        ImageVector.Builder(
            name = "ChevronDown",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(7.41f, 8.59f)
                lineTo(12f, 13.17f)
                lineTo(16.59f, 8.59f)
                lineTo(18f, 10f)
                lineTo(12f, 16f)
                lineTo(6f, 10f)
                close()
            }
        }.build()
    }

    val Search: ImageVector by lazy {
        ImageVector.Builder(
            name = "Search",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(15.5f, 14f)
                horizontalLineTo(14.71f)
                lineTo(14.43f, 13.73f)
                curveTo(15.41f, 12.59f, 16f, 11.11f, 16f, 9.5f)
                curveTo(16f, 5.91f, 13.09f, 3f, 9.5f, 3f)
                reflectiveCurveTo(3f, 5.91f, 3f, 9.5f)
                reflectiveCurveTo(5.91f, 16f, 9.5f, 16f)
                curveTo(11.11f, 16f, 12.59f, 15.41f, 13.73f, 14.43f)
                lineTo(14f, 14.71f)
                verticalLineTo(15.5f)
                lineTo(19f, 20.49f)
                lineTo(20.49f, 19f)
                lineTo(15.5f, 14f)
                close()
                moveTo(9.5f, 14f)
                curveTo(7.01f, 14f, 5f, 11.99f, 5f, 9.5f)
                reflectiveCurveTo(7.01f, 5f, 9.5f, 5f)
                reflectiveCurveTo(14f, 7.01f, 14f, 9.5f)
                reflectiveCurveTo(11.99f, 14f, 9.5f, 14f)
                close()
            }
        }.build()
    }

    val Check: ImageVector by lazy {
        ImageVector.Builder(
            name = "Check",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(9f, 16.17f)
                lineTo(4.83f, 12f)
                lineTo(3.41f, 13.41f)
                lineTo(9f, 19f)
                lineTo(21f, 7f)
                lineTo(19.59f, 5.59f)
                close()
            }
        }.build()
    }

    val CheckCircle: ImageVector by lazy {
        ImageVector.Builder(
            name = "CheckCircle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveTo(6.48f, 22f, 12f, 22f)
                reflectiveCurveTo(22f, 17.52f, 22f, 12f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(10f, 17f)
                lineTo(5f, 12f)
                lineTo(6.41f, 10.59f)
                lineTo(10f, 14.17f)
                lineTo(17.59f, 6.58f)
                lineTo(19f, 8f)
                lineTo(10f, 17f)
                close()
            }
        }.build()
    }

    val Close: ImageVector by lazy {
        ImageVector.Builder(
            name = "Close",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(19f, 6.41f)
                lineTo(17.59f, 5f)
                lineTo(12f, 10.59f)
                lineTo(6.41f, 5f)
                lineTo(5f, 6.41f)
                lineTo(10.59f, 12f)
                lineTo(5f, 17.59f)
                lineTo(6.41f, 19f)
                lineTo(12f, 13.41f)
                lineTo(17.59f, 19f)
                lineTo(19f, 17.59f)
                lineTo(13.41f, 12f)
                close()
            }
        }.build()
    }

    val Add: ImageVector by lazy {
        ImageVector.Builder(
            name = "Add",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(19f, 13f)
                horizontalLineTo(13f)
                verticalLineTo(19f)
                horizontalLineTo(11f)
                verticalLineTo(13f)
                horizontalLineTo(5f)
                verticalLineTo(11f)
                horizontalLineTo(11f)
                verticalLineTo(5f)
                horizontalLineTo(13f)
                verticalLineTo(11f)
                horizontalLineTo(19f)
                verticalLineTo(13f)
                close()
            }
        }.build()
    }

    val Info: ImageVector by lazy {
        ImageVector.Builder(
            name = "Info",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveTo(6.48f, 22f, 12f, 22f)
                reflectiveCurveTo(22f, 17.52f, 22f, 12f)
                reflectiveCurveTo(17.52f, 2f, 12f, 2f)
                close()
                moveTo(13f, 17f)
                horizontalLineTo(11f)
                verticalLineTo(11f)
                horizontalLineTo(13f)
                verticalLineTo(17f)
                close()
                moveTo(13f, 9f)
                horizontalLineTo(11f)
                verticalLineTo(7f)
                horizontalLineTo(13f)
                verticalLineTo(9f)
                close()
            }
        }.build()
    }

    val Warning: ImageVector by lazy {
        ImageVector.Builder(
            name = "Warning",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(1f, 21f)
                horizontalLineTo(23f)
                lineTo(12f, 2f)
                lineTo(1f, 21f)
                close()
                moveTo(13f, 18f)
                horizontalLineTo(11f)
                verticalLineTo(16f)
                horizontalLineTo(13f)
                verticalLineTo(18f)
                close()
                moveTo(13f, 14f)
                horizontalLineTo(11f)
                verticalLineTo(10f)
                horizontalLineTo(13f)
                verticalLineTo(14f)
                close()
            }
        }.build()
    }

    val User: ImageVector by lazy {
        ImageVector.Builder(
            name = "User",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 12f)
                curveTo(14.21f, 12f, 16f, 10.21f, 16f, 8f)
                reflectiveCurveTo(14.21f, 4f, 12f, 4f)
                reflectiveCurveTo(8f, 5.79f, 8f, 8f)
                reflectiveCurveTo(9.79f, 12f, 12f, 12f)
                close()
                moveTo(12f, 14f)
                curveTo(9.33f, 14f, 4f, 15.34f, 4f, 18f)
                verticalLineTo(20f)
                horizontalLineTo(20f)
                verticalLineTo(18f)
                curveTo(20f, 15.34f, 14.67f, 14f, 12f, 14f)
                close()
            }
        }.build()
    }

    val Calendar: ImageVector by lazy {
        ImageVector.Builder(
            name = "Calendar",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(19f, 4f)
                horizontalLineTo(18f)
                verticalLineTo(2f)
                horizontalLineTo(16f)
                verticalLineTo(4f)
                horizontalLineTo(8f)
                verticalLineTo(2f)
                horizontalLineTo(6f)
                verticalLineTo(4f)
                horizontalLineTo(5f)
                curveTo(3.89f, 4f, 3.01f, 4.9f, 3.01f, 6f)
                lineTo(3f, 20f)
                curveTo(3f, 21.1f, 3.89f, 22f, 5f, 22f)
                horizontalLineTo(19f)
                curveTo(20.1f, 22f, 21f, 21.1f, 21f, 20f)
                verticalLineTo(6f)
                curveTo(21f, 4.9f, 20.1f, 4f, 19f, 4f)
                close()
                moveTo(19f, 20f)
                horizontalLineTo(5f)
                verticalLineTo(10f)
                horizontalLineTo(19f)
                verticalLineTo(20f)
                close()
            }
        }.build()
    }

    val Clock: ImageVector by lazy {
        ImageVector.Builder(
            name = "Clock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(11.99f, 2f)
                curveTo(6.47f, 2f, 2f, 6.48f, 2f, 12f)
                reflectiveCurveTo(6.47f, 22f, 11.99f, 22f)
                curveTo(17.52f, 22f, 22f, 17.52f, 22f, 12f)
                reflectiveCurveTo(17.52f, 2f, 11.99f, 2f)
                close()
                moveTo(12f, 20f)
                curveTo(7.58f, 20f, 4f, 16.42f, 4f, 12f)
                reflectiveCurveTo(7.58f, 4f, 12f, 4f)
                reflectiveCurveTo(20f, 7.58f, 20f, 12f)
                reflectiveCurveTo(16.42f, 20f, 12f, 20f)
                close()
                moveTo(12.5f, 7f)
                horizontalLineTo(11f)
                verticalLineTo(13f)
                lineTo(16.25f, 16.15f)
                lineTo(17f, 14.92f)
                lineTo(12.5f, 12.25f)
                verticalLineTo(7f)
                close()
            }
        }.build()
    }

    val Document: ImageVector by lazy {
        ImageVector.Builder(
            name = "Document",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(14f, 2f)
                horizontalLineTo(6f)
                curveTo(4.9f, 2f, 4.01f, 2.9f, 4.01f, 4f)
                lineTo(4f, 20f)
                curveTo(4f, 21.1f, 4.89f, 22f, 5.99f, 22f)
                horizontalLineTo(18f)
                curveTo(19.1f, 22f, 20f, 21.1f, 20f, 20f)
                verticalLineTo(8f)
                lineTo(14f, 2f)
                close()
                moveTo(16f, 18f)
                horizontalLineTo(8f)
                verticalLineTo(16f)
                horizontalLineTo(16f)
                verticalLineTo(18f)
                close()
                moveTo(16f, 14f)
                horizontalLineTo(8f)
                verticalLineTo(12f)
                horizontalLineTo(16f)
                verticalLineTo(14f)
                close()
                moveTo(13f, 9f)
                verticalLineTo(3.5f)
                lineTo(18.5f, 9f)
                horizontalLineTo(13f)
                close()
            }
        }.build()
    }

    val Building: ImageVector by lazy {
        ImageVector.Builder(
            name = "Building",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 1f)
                lineTo(3f, 5f)
                verticalLineTo(7f)
                horizontalLineTo(21f)
                verticalLineTo(5f)
                lineTo(12f, 1f)
                close()
                moveTo(5f, 9f)
                verticalLineTo(17f)
                horizontalLineTo(7f)
                verticalLineTo(9f)
                horizontalLineTo(5f)
                close()
                moveTo(11f, 9f)
                verticalLineTo(17f)
                horizontalLineTo(13f)
                verticalLineTo(9f)
                horizontalLineTo(11f)
                close()
                moveTo(17f, 9f)
                verticalLineTo(17f)
                horizontalLineTo(19f)
                verticalLineTo(9f)
                horizontalLineTo(17f)
                close()
                moveTo(2f, 19f)
                verticalLineTo(22f)
                horizontalLineTo(22f)
                verticalLineTo(19f)
                horizontalLineTo(2f)
                close()
            }
        }.build()
    }

    val Phone: ImageVector by lazy {
        ImageVector.Builder(
            name = "Phone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(6.62f, 10.79f)
                curveTo(8.06f, 13.62f, 10.38f, 15.94f, 13.21f, 17.38f)
                lineTo(15.41f, 15.18f)
                curveTo(15.69f, 14.9f, 16.08f, 14.82f, 16.43f, 14.93f)
                curveTo(17.55f, 15.3f, 18.75f, 15.5f, 20f, 15.5f)
                curveTo(20.55f, 15.5f, 21f, 15.95f, 21f, 16.5f)
                verticalLineTo(20f)
                curveTo(21f, 20.55f, 20.55f, 21f, 20f, 21f)
                curveTo(10.61f, 21f, 3f, 13.39f, 3f, 4f)
                curveTo(3f, 3.45f, 3.45f, 3f, 4f, 3f)
                horizontalLineTo(7.5f)
                curveTo(8.05f, 3f, 8.5f, 3.45f, 8.5f, 4f)
                curveTo(8.5f, 5.25f, 8.7f, 6.45f, 9.07f, 7.57f)
                curveTo(9.18f, 7.92f, 9.1f, 8.31f, 8.82f, 8.59f)
                lineTo(6.62f, 10.79f)
                close()
            }
        }.build()
    }

    val Home: ImageVector by lazy {
        ImageVector.Builder(
            name = "Home",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(10f, 20f)
                verticalLineTo(14f)
                horizontalLineTo(14f)
                verticalLineTo(20f)
                horizontalLineTo(19f)
                verticalLineTo(12f)
                horizontalLineTo(22f)
                lineTo(12f, 3f)
                lineTo(2f, 12f)
                horizontalLineTo(5f)
                verticalLineTo(20f)
                close()
            }
        }.build()
    }

    val Menu: ImageVector by lazy {
        ImageVector.Builder(
            name = "Menu",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(3f, 18f)
                horizontalLineTo(21f)
                verticalLineTo(16f)
                horizontalLineTo(3f)
                verticalLineTo(18f)
                close()
                moveTo(3f, 13f)
                horizontalLineTo(21f)
                verticalLineTo(11f)
                horizontalLineTo(3f)
                verticalLineTo(13f)
                close()
                moveTo(3f, 6f)
                verticalLineTo(8f)
                horizontalLineTo(21f)
                verticalLineTo(6f)
                horizontalLineTo(3f)
                close()
            }
        }.build()
    }

    val Upload: ImageVector by lazy {
        ImageVector.Builder(
            name = "Upload",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(9f, 16f)
                horizontalLineTo(15f)
                verticalLineTo(10f)
                horizontalLineTo(19f)
                lineTo(12f, 3f)
                lineTo(5f, 10f)
                horizontalLineTo(9f)
                verticalLineTo(16f)
                close()
                moveTo(5f, 18f)
                horizontalLineTo(19f)
                verticalLineTo(20f)
                horizontalLineTo(5f)
                verticalLineTo(18f)
                close()
            }
        }.build()
    }

    val Delete: ImageVector by lazy {
        ImageVector.Builder(
            name = "Delete",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(6f, 19f)
                curveTo(6f, 20.1f, 6.9f, 21f, 8f, 21f)
                horizontalLineTo(16f)
                curveTo(17.1f, 21f, 18f, 20.1f, 18f, 19f)
                verticalLineTo(7f)
                horizontalLineTo(6f)
                verticalLineTo(19f)
                close()
                moveTo(19f, 4f)
                horizontalLineTo(15.5f)
                lineTo(14.5f, 3f)
                horizontalLineTo(9.5f)
                lineTo(8.5f, 4f)
                horizontalLineTo(5f)
                verticalLineTo(6f)
                horizontalLineTo(19f)
                verticalLineTo(4f)
                close()
            }
        }.build()
    }

    val Lock: ImageVector by lazy {
        ImageVector.Builder(
            name = "Lock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(18f, 8f)
                horizontalLineTo(17f)
                verticalLineTo(6f)
                curveTo(17f, 3.24f, 14.76f, 1f, 12f, 1f)
                reflectiveCurveTo(7f, 3.24f, 7f, 6f)
                verticalLineTo(8f)
                horizontalLineTo(6f)
                curveTo(4.9f, 8f, 4f, 8.9f, 4f, 10f)
                verticalLineTo(20f)
                curveTo(4f, 21.1f, 4.9f, 22f, 6f, 22f)
                horizontalLineTo(18f)
                curveTo(19.1f, 22f, 20f, 21.1f, 20f, 20f)
                verticalLineTo(10f)
                curveTo(20f, 8.9f, 19.1f, 8f, 18f, 8f)
                close()
                moveTo(9f, 6f)
                curveTo(9f, 4.34f, 10.34f, 3f, 12f, 3f)
                reflectiveCurveTo(15f, 4.34f, 15f, 6f)
                verticalLineTo(8f)
                horizontalLineTo(9f)
                verticalLineTo(6f)
                close()
            }
        }.build()
    }

    val Refresh: ImageVector by lazy {
        ImageVector.Builder(
            name = "Refresh",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(17.65f, 6.35f)
                curveTo(16.2f, 4.9f, 14.21f, 4f, 12f, 4f)
                curveTo(7.58f, 4f, 4.01f, 7.58f, 4.01f, 12f)
                reflectiveCurveTo(7.58f, 20f, 12f, 20f)
                curveTo(15.73f, 20f, 18.84f, 17.45f, 19.73f, 14f)
                horizontalLineTo(17.65f)
                curveTo(16.83f, 16.33f, 14.61f, 18f, 12f, 18f)
                curveTo(8.69f, 18f, 6f, 15.31f, 6f, 12f)
                reflectiveCurveTo(8.69f, 6f, 12f, 6f)
                curveTo(13.66f, 6f, 15.14f, 6.69f, 16.22f, 7.78f)
                lineTo(13f, 11f)
                horizontalLineTo(20f)
                verticalLineTo(4f)
                lineTo(17.65f, 6.35f)
                close()
            }
        }.build()
    }

    val LogOut: ImageVector by lazy {
        ImageVector.Builder(
            name = "LogOut",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(17f, 7f)
                lineTo(15.59f, 8.41f)
                lineTo(18.17f, 11f)
                horizontalLineTo(8f)
                verticalLineTo(13f)
                horizontalLineTo(18.17f)
                lineTo(15.59f, 15.58f)
                lineTo(17f, 17f)
                lineTo(22f, 12f)
                lineTo(17f, 7f)
                close()
                moveTo(4f, 5f)
                horizontalLineTo(12f)
                verticalLineTo(3f)
                horizontalLineTo(4f)
                curveTo(2.9f, 3f, 2f, 3.9f, 2f, 5f)
                verticalLineTo(19f)
                curveTo(2f, 20.1f, 2.9f, 21f, 4f, 21f)
                horizontalLineTo(12f)
                verticalLineTo(19f)
                horizontalLineTo(4f)
                verticalLineTo(5f)
                close()
            }
        }.build()
    }

    val Shield: ImageVector by lazy {
        ImageVector.Builder(
            name = "Shield",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(12f, 1f)
                lineTo(3f, 5f)
                verticalLineTo(11f)
                curveTo(3f, 16.55f, 6.84f, 21.74f, 12f, 23f)
                curveTo(17.16f, 21.74f, 21f, 16.55f, 21f, 11f)
                verticalLineTo(5f)
                lineTo(12f, 1f)
                close()
            }
        }.build()
    }

    val HelpOutline: ImageVector by lazy {
        ImageVector.Builder(
            name = "HelpOutline",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(11f, 18f)
                horizontalLineTo(13f)
                verticalLineTo(16f)
                horizontalLineTo(11f)
                verticalLineTo(18f)
                close()
                moveTo(12f, 2f)
                curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
                curveTo(2f, 17.52f, 6.48f, 22f, 12f, 22f)
                curveTo(17.52f, 22f, 22f, 17.52f, 22f, 12f)
                curveTo(22f, 6.48f, 17.52f, 2f, 12f, 2f)
                close()
                moveTo(12f, 20f)
                curveTo(7.59f, 20f, 4f, 16.41f, 4f, 12f)
                curveTo(4f, 7.59f, 7.59f, 4f, 12f, 4f)
                curveTo(16.41f, 4f, 20f, 7.59f, 20f, 12f)
                curveTo(20f, 16.41f, 16.41f, 20f, 12f, 20f)
                close()
                moveTo(12f, 6f)
                curveTo(9.79f, 6f, 8f, 7.79f, 8f, 10f)
                horizontalLineTo(10f)
                curveTo(10f, 8.9f, 10.9f, 8f, 12f, 8f)
                curveTo(13.1f, 8f, 14f, 8.9f, 14f, 10f)
                curveTo(14f, 12f, 11f, 11.75f, 11f, 15f)
                horizontalLineTo(13f)
                curveTo(13f, 12.75f, 16f, 12.5f, 16f, 10f)
                curveTo(16f, 7.79f, 14.21f, 6f, 12f, 6f)
                close()
            }
        }.build()
    }
}
