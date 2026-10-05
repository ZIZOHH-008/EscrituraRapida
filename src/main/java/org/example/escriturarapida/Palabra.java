package org.example.escriturarapida;

/**
 * Data repository containing word banks and feedback phrases.
 * Stores target string arrays categorized by difficulty tier and feedback messages.
 *
 * @author Juan Parra
 * @author Brian Rodríguez
 * @author Nicolas Martínez
 * @version 1.0
 */
public class Palabra {

    /** Array of easy difficulty words for initial game levels. */
    public static String[] palabrasFaciles = {
            "Pepe",
            "Rodolfo",
            "JavaFX",
            "Mártir",
            "Hola mundo",
            "Computadora",
            "Teclado",
            "Ventana",
            "Desarrollo",
            "Algoritmo",
            "Variable",
            "Método",
            "Controlador",
            "Interfaz",
            "Software",
            "Aplicación",
            "Proyecto",
            "Tecnología",
            "Estudiante",
            "Universidad",
            "Profesor",
            "Programa",
            "Programación",
            "Computación",
            "Informática",
            "Ingeniería",
            "Electricidad",
            "Matemática",
            "Geometría",
            "Conjunto",
            "Función",
    };

    /** Array of medium difficulty words and short phrases. */
    public static String[] palabrasMedias = {
            "Escritura rápida",
            "Código fuente",
            "Programación",
            "Inteligencia",
            "Desarrollo",
            "Sistema operativo",
            "Computación",
            "Tecnología",
            "Aplicación",
            "Información",
            "Configuración",
            "Comunicación",
            "Organización",
            "Estructura de datos",
            "Base de datos",
            "Interfaz gráfica",
            "Proyecto académico",
            "Sistema informático",
            "Archivo de texto",
            "Red de computadores",
            "Conexión inalámbrica",
            "Seguridad informática",
            "Diseño de software",
            "Desarrollo web",
            "Página principal",
            "Menú de opciones"
    };

    /** Array of hard difficulty terms and longer phrases. */
    public static String[] palabrasDificiles = {
            "Programación avanzada",
            "Administración pública",
            "Configuración avanzada",
            "Implementación tecnológica",
            "Investigación científica",
            "Documentación técnica",
            "Comunicación digital",
            "Optimización computacional",
            "Arquitectura informática",
            "Funcionamiento interno",
            "Procesamiento paralelo",
            "Almacenamiento electrónico",
            "Desarrollador profesional",
            "Computación cuántica",
            "Electrónica digital",
            "Matemáticas aplicadas",
            "Estadística descriptiva",
            "Probabilidad matemática",
            "Representación gráfica",
            "Transformación digital",
            "Caracterización numérica",
            "Interpretación matemática",
            "Experimentación científica",
            "Clasificación automática",
            "Identificación biométrica",
            "Multiplicación matricial",
            "Personalización avanzada",
            "Conectividad inalámbrica",
            "Compatibilidad tecnológica",
            "Interactividad gráfica",
            "Productividad académica",
            "Accesibilidad digital",
            "Administración informática",
            "Desarrollo tecnológico",
            "Análisis computacional",
            "Algoritmo matemático",
            "Estructuración jerárquica",
            "Programación orientada",
            "Virtualización informática"
    };

    /** Array of extremely complex terms for maximum difficulty tier. */
    public static String[] palabrasImposibles = {
            "Desoxirribonucleico",
            "Esternocleidomastoideo",
            "Otorrinolaringólogo",
            "Electroencefalograma",
            "Electrocardiograma",
            "Paralelepípedo",
            "Anticonstitucionalmente",
            "Inconstitucionalidad",
            "Extraordinariamente",
            "Ovivíparo",
            "Hermafrodita",
            "Arqueopterix",
            "Inmunodeficiencia",
            "Neurotransmisor",
            "Fotosíntesis",
            "Microorganismo",
            "Metamorfosis",
            "Idiosincrasia",
            "Epistemología",
            "Ontológicamente",
            "Circunferencia",
            "Perpendicularidad",
            "Irreversibilidad",
            "Ininteligible",
            "Otorrinolaringología",
            "Electrodoméstico",
            "Termodinámica",
            "Espectrofotómetro",
            "Cromatografía",
            "Cristalización",
            "Descontextualización",
            "Incompatibilidad",
            "Extraordinario",
            "Quimiosíntesis",
            "Fotosintéticamente",
            "Hipercolesterolemia",
            "Gastroenterología",
            "Neurodegenerativo",
            "Inmunohistoquímica",
            "Electromagnetismo"
    };

    /** Random success feedback strings displayed upon correct input. */
    public static String[] exitoso = {
            "¡Correcto!",
            "¡Nivel superado!",
            "¡Excelente!",
            "La Cabra"
    };

    /** Random failure feedback strings displayed upon incorrect input. */
    public static String[] fracasado = {
            "¡Incorrecto!",
            "¡Casi lo logras!",
            "¿Quieres unas gafas?"
    };
}