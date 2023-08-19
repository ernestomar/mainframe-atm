# Ejemplo de Cajero Automático como si fuera Mainframe.

## Funcionalidad

**Práctica de Línea de Comandos: Simulación de un Cajero Automático**

**Objetivo:**  
Desarrollar un programa interactivo en interfas de usuario que simule las operaciones básicas de un cajero automático.

**Instrucciones:**

1. **Inicialización del Programa**:

   - Al iniciar el programa, se debe solicitar al usuario que ingrese su alias(usuario den el gui) y un PIN (Número de Identificación Personal) de 4 dígitos.
   - Si el PIN es incorrecto después de 3 intentos, el programa debe cerrarse con un mensaje de error.
   - Si el PIN es correcto, el usuario puede acceder al menú principal.

2. **Menú Principal**:

   - El menú principal debe ofrecer las siguientes opciones:
     1. Consultar saldo.
     2. Realizar un depósito.
     3. Realizar un retiro.
     4. Cambiar PIN.
     5. Salir.

3. **Consultar Saldo**:

   - Mostrar el saldo actual del usuario.

4. **Realizar un Depósito**:

   - Solicitar al usuario que ingrese la cantidad que desea depositar.
   - Validar que la cantidad sea positiva.
   - Añadir la cantidad al saldo actual.
   - Mostrar un mensaje de confirmación.

5. **Realizar un Retiro**:

   - Solicitar al usuario que ingrese la cantidad que desea retirar.
   - Validar que la cantidad sea positiva y que no exceda el saldo actual.
   - Restar la cantidad del saldo actual.
   - Mostrar un mensaje de confirmación.

6. **Cambiar PIN**:

   - Solicitar al usuario que ingrese su PIN actual.
   - Si el PIN ingresado es correcto, pedir que ingrese el nuevo PIN.
   - Solicitar que confirme el nuevo PIN ingresándolo nuevamente.
   - Si ambos PINs coinciden, actualizar el PIN.

7. **Salir**:
   - Mostrar un mensaje de despedida y cerrar el programa.

## Requerimientos

Para correr este proyecto debe tener instalado:

- Java 11
- Maven 3.8.4

Se recomienda utilizar sdkman (Linux)

## Compilación

Para compilar el proyecto

```
mvn clean install
```

## Ejecución

```
mvn exec:java -Dexec.mainClass="bo.edu.ucb.sis213.Main"
```

## Instalación de la Base de Datos

1. Hacer correr una instancia MySQL en docker

```
docker run --name mysql-atm -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:8
```

2. Me conecto a la BBDD (Le pedira password es 123456)

```
docker exec -it mysql-atm mysql -u root -p
```

3. Creamos la Base de Datos del ATM.

```
CREATE DATABASE atm;
```

4. Creamos la Base de Datos del ATM.

```
use atm;
```

5. Ejecutan el script init.sql de la carpeta database.

---

Nota: Una vez hecho el paso 1, para volver a correr el contenedor de docker(que tiene la base de datos)

```
docker start mysql-atm
```

Tambien verificar tener mysql apagado

### Linux:

```
sudo service mysql stop
```

### Windows:

Windows+R: services.msc
Buscar mysql
CLick derecho y darle en "Detener"

---

# Capturas de pantalla finales:

Bienvenida:
![Bienvenda](/mainframe-atm/images_figma/Screenshot%20from%202023-08-19%2017-26-03.png)
Interfaz inicial:
![Bienvenda](/mainframe-atm/images_figma/Screenshot%20from%202023-08-19%2017-26-10.png)

Consulta de saldo:
![Bienvenda](/mainframe-atm/images_figma/Screenshot%20from%202023-08-19%2017-26-15.png)

Realizando retiro:
![Bienvenda](/mainframe-atm/images_figma/Screenshot%20from%202023-08-19%2017-26-18.png)

Realizando deposito:
![Bienvenda](/mainframe-atm/images_figma/Screenshot%20from%202023-08-19%2017-26-22.png)

Cambio de PIN:
![Bienvenda](/mainframe-atm/images_figma/Screenshot%20from%202023-08-19%2017-26-26.png)

---

# Diseño de la interfaz en FIGMA:

Link:https://www.figma.com/file/MbnVrPWFMKVBYlolxlxUoG/ATM-Interfaz-de-usuario?type=design&node-id=0%3A1&mode=design&t=JkVmRgid1BEL3XrT-1

Bienvenida:
![Bienvenda](/mainframe-atm/images_figma/BienvenidaBanco.png)

Interfaz iniciado sesion
![Interfaz inicial](/mainframe-atm/images_figma/BienvenidaUsuario.png)
Deposito de dinero
![Deposito](/mainframe-atm/images_figma/RealizarDeposito.png)
Retiro de dinero
![Retiro](/mainframe-atm/images_figma/RealizarRetiro.png)
Cambio de pin:
![Cambio de PIN](/mainframe-atm/images_figma/CambiarPIN1.png)
