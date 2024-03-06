# Desafio - SoftExpert - Java Pleno
Repositório para desafio técnico da SoftExpert para vaga de desenvolvedor Java Pleno.

Tecnologias Utilizadas:
 - Back-end: Spring Boot, Java 11, Lombok
 - Front-end: Angular 12

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

# Cobertura de Testes (IDE Intellij IDEA)
Obs: Praticamente todas as classes foram testadas, desde DTO's, classes de apoio (Helpers), Services, Controller e Classes de Exception.

Utilizados nos testes: JUnit, Mockito e AssertJ.

![Captura de tela 2024-03-05 162322](https://github.com/feilonn/desafio-SE/assets/60004704/0acde07a-40de-4a68-bfbf-cc79dc0edcdd)
![Captura de tela 2024-03-05 162017](https://github.com/feilonn/desafio-SE/assets/60004704/fabad142-7a5d-41b7-8433-8e33e8262a03)

# Documentação da API - Swagger
![image](https://github.com/feilonn/desafio-SE/assets/60004704/cd5a434c-7c78-4fd4-801b-151e8dc9a75a)

# Configuração de CORS
![image](https://github.com/feilonn/desafio-SE/assets/60004704/6cd11be9-dc64-4223-b1c8-92d735fbc271)


# Video demonstrativo da requisição através do Front-end
![softexpert-gif](https://github.com/feilonn/desafio-SE/assets/60004704/513dfca7-1fc5-4c22-ac9a-5995bbe30d3f)

# Banco Digital Utilizado
- O banco digital utilizado nesta aplicação foi o Efí: https://dev.efipay.com.br/. O Serviço retorna um link que exibe uma pagina web contendo tanto o QrCode do PIX, como também a opção de compartilhar o código via WhatsApp.
- É imprescindível que, durante a requisição ao serviço da Efí, seja informado uma chave PIX válida registrada nesse banco. Outro ponto é que se faz necessário a criação de um certificado na plataforma da Efí, e que deve ser utilizado na sua aplicação back-end para poder assim consumir o serviço.
- Também é necessário ter em posse as informações de Client ID e Client Secret que também são disponibilizados na plataforma da Efí.
  
- A aplicação está preparada para receber descontos e acrescimos tanto em valores inteiros, por exemplo: R$ 20.00, como também em porcentagem. Foram criados ENUM's para esse controle e no front-end existe um input do tipo select onde é possível alterar entre essas opções.
  
![image](https://github.com/feilonn/desafio-SE/assets/60004704/018adfc4-a0cb-4d0a-948f-2ee5aa0b31de)
![image](https://github.com/feilonn/desafio-SE/assets/60004704/ffecbb57-979b-4798-bc45-e8b3e984e5cc)

# Exemplo dos Links de Pagamentos Gerados
![pix1_tarja](https://github.com/feilonn/desafio-SE/assets/60004704/37bd0032-24e5-4362-9fdd-5c77fa84d61a)
![pix2_tarja](https://github.com/feilonn/desafio-SE/assets/60004704/c328dc01-32ec-4f42-9696-26873166f8b0)

# Exemplo de Requisição com mais de duas pessoa (Um dos requisitos do desafio)
- Obs: O print do exemplo foi executado no Insomnia, mas o front-end também está preparado para executar a requisição com quantas pessoas forem necessárias

![image](https://github.com/feilonn/desafio-SE/assets/60004704/c7024151-d4b0-442c-8b94-fdbfdb2ccd39)

# Informações Adicionais
- O método de pagamento implementado nessa ocasião foi o de PIX, mas a aplicação está pronta para ser integrada com demais outras através um controlador baseado em ENUM.
<br>Obs: No print com o trecho de código do 'switch', existe uma opção que está comentada somente para exemplicar como ficaria o código quando houvessem novas formas de pagamento disponível.

![image](https://github.com/feilonn/desafio-SE/assets/60004704/6a0c5be6-fcce-4a8d-ac08-bb875c544336)
![image](https://github.com/feilonn/desafio-SE/assets/60004704/93d655a8-41c3-48b2-b65c-1eeff82c65a6)

- Foi utilizando a tipagem para os dados numéricos como BigDecimal por se tratar de uma aplicação que resulta em uma operação financeira. O BigDecimal possui vantagens no quisito de precisão, arredondamento e evita erros que podem ocorrer ao se utilizar double, por exemplo.
- Durante o desenvolvimento do código foram levadas em considerações as convenções de código limpo, como: Nomes intuitivos e precisos para variáveis, métodos e classes; Reaproveitamento de código; Divisão de Responsabilidades, etc.
- Foi também considerado padrões de qualidade de código (Sonar Lint, SonarQube). Obedecendo por exemplo: Tamanho máximo de linha de código, Remoção de Imports não utilizados, Cobertura de Testes e etc. 
- O projeto do front-end é apenas algo básico somente para afetuar a requisição via browser (assim como descrito no desafio técnico).
