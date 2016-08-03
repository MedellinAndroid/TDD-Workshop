# TDD Android por pasos

Este proyecto muestra paso a paso como desarrollar una aplicación usando el paso de separación o 
patrón de diseño MVP (Model View Presenter).

### Paso 1
* Escribir el test en el cual el presentador contiene sus dependencias directas (view e interactor)
* El test debera validar mediante verificaciones de mockito que el comportamiento es correcto
* la primera verificación es la mas simple, que al dar click en el boton de login se muestre la 
pantalla de carga

### Paso 2
* Escribir el test en el cual el presentador chequea la interaccion con el caso de uso y este falla
* Se usan los ArgumentCaptors para simular los llamados de los colaboradores
