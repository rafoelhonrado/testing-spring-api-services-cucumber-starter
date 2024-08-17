# language: es
@Actualizar
Requisito: Actualizar un producto usando Antecedentes

	Antecedentes:
		Dada un producto con nombre "Nombre original"
		* y la descripción "Descripción original"
		* y el precio 1500
		* previamente creado con exito usando la ruta "/api/v1/product/" y metodo "POST"
	
	Esquema del escenario: Actualizar un producto creado previamente con éxito
	Cuando se hace una petición "PUT" con el nombre "<nombre>"
	* y la descripción "<descripcion>"
	* y el precio <precio>
	* a la API
	Entonces se recibe una respuesta "exitosa" con código <codigo>
	* y el mensaje "<mensaje>"
	* con los campos actualizados correctamente

	@Exito
	Ejemplos:
	|nombre|descripcion|precio|codigo|mensaje|
	|Myphone 18|Descripcion Actualizada|999|200|El producto fue actualizado con éxito|
	|Myphone 19|Descripcion Actualizada|999|200|El producto fue actualizado con éxito|