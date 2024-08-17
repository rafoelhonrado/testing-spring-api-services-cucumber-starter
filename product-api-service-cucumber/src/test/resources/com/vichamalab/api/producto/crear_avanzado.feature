# language: es
@Crear
Requisito: Crear un nuevo producto usando la api /api/v1/product

	Esquema del escenario: Crear un producto con éxito
	Dada una API válida con url "/api/v1/product/" y formato "application/json"
	Cuando se hace una petición "POST" con el nombre "<nombre>"
	* y la descripción "<descripcion>"
	* y el precio <precio>
	* a la API
	Entonces se recibe una respuesta "exitosa" con código <codigo>
	* y el mensaje "<mensaje>"

	@Cat1
	Ejemplos: Categoria 1
	|nombre|descripcion|precio|codigo|mensaje|
	|Myphone 11|Un smartphone MyPhone|1100|201|El producto fue creado con éxito!|
	|Myphone 11|Un smartphone MyPhone|1100|201|El producto fue creado con éxito!|
	
	@Cat2
	Ejemplos: Categoria 2
	|nombre|descripcion|precio|codigo|mensaje|
	|Myphone 11|Un smartphone MyPhone|1100|201|El producto fue creado con éxito!|
	|Myphone 11|Un smartphone MyPhone|1100|201|El producto fue creado con éxito!|