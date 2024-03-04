# desafio-SE
Repositório para desafio técnico da SoftExpert para vaga de desenvolvedor Java Pleno.

# Documentação - Swagger

# Consumo do Serviço - /food/calculaValoresPedido - POST
<br>

Exemplo de Body para a requisição (JSON):
<br><br>
{
	"valorFrete": 8.0,
	"desconto": 20.0,
	"acrescimo": 0,
	"tipoDesconto": "INTEIRO",
	"tipoAcrescimo": "INTEIRO",
	"tipoPagamento": "PIX",
	"pedidos": [
		{
			"itensPedido": [
				{
					"tituloItem": "Hamburguer",
					"valorItem": 40.0
				},
				{
					"tituloItem": "Sobremesa",
					"valorItem": 2.0
				}
			],
			"nomeSolicitante": "Joao"
		},
		{
			"itensPedido": [
				{
					"tituloItem": "Sanduiche",
					"valorItem": 8.0
				}
			],
			"nomeSolicitante": "Maria"
		}
	]
}
<br><br>
Exemplo de Response (JSON):
<br><br>
[
	{
		"itensPedido": [
			{
				"tituloItem": "Hamburguer",
				"valorItem": 40.0
			},
			{
				"tituloItem": "Sobremesa",
				"valorItem": 2.0
			}
		],
		"nomeSolicitante": "Joao",
		"valorFinalParaPagar": "31.92",
		"urlPix": "https://pix.sejaefi.com.br/cob/pagar/{code}"
	},
	{
		"itensPedido": [
			{
				"tituloItem": "Sanduiche",
				"valorItem": 8.0
			}
		],
		"nomeSolicitante": "Maria",
		"valorFinalParaPagar": "6.08",
		"urlPix": "https://pix.sejaefi.com.br/cob/pagar/{code}"
	}
]
