# Sistema de Venta de Entradas - Teatro Moro

Este proyecto en Java simula un sistema de reserva y venta de entradas para el Teatro Moro, gestionando asientos, descuentos y emisión de boletas. El sistema fue desarrollado como parte de la actividad sumativa de la semana 6

## Funcionalidades

1. **Reservar entradas**  
   El usuario puede seleccionar un asiento y asignar una ubicación (VIP, Platea o General).

2. **Comprar entradas**  
   Se puede comprar un asiento libre o reservado. Se aplican descuentos automáticos:
   - 10% para estudiantes
   - 15% para personas mayores de 65 años

3. **Modificar una venta existente**  
   Permite modificar los datos de edad y condición del comprador de un asiento ya comprado, recalculando el precio con el nuevo descuento.

4. **Imprimir boleta**  
   Genera una boleta con la información completa de la compra: número de asiento, ubicación, cantidad y total pagado.

5. **Salir**  
   Cierra el programa.

## Variables utilizadas

- Variables estáticas: nombre del teatro, capacidad, precio base, contador de ventas, ingresos totales.
- Variables de instancia: estado de cada asiento, ubicación, precio final, cantidad.
- Variables locales: edad, opción seleccionada, descuento aplicado, tipo de entrada, entre otras.

## Estructuras aplicadas

- Ciclos: `while` para el menú principal, validaciones.
- Condicionales: `if`, `else`, `switch` para opciones y lógicas de negocio.
- Modularización: funciones separadas por acción (`reservarAsiento`, `comprarAsiento`, etc).

## Puntos de depuración sugeridos

- En la conversión de una reserva a compra (`asientos[asiento] = 2;`)
- En la selección y validación de asientos (`mostrarDisponibilidad`, `reservarAsiento`)
- En la impresión de boleta (`ubicaciones[asiento]`, `precios[asiento]`)

## Requisitos

- Java JDK 17+
- Apache NetBeans IDE

## Ejecución

Compilar y ejecutar el archivo `TeatroMoro.java` en NetBeans o cualquier otro IDE de Java. Usar la consola para interactuar con el menú y probar cada funcionalidad.

---
