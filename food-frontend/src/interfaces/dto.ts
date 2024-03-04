export interface PagamentoDetalhesDTO {
    itensPedido: ItemPedido[];
    nomeSolicitante: string;
    valorFinalParaPagar: number;
    urlPix: string;
  }
  
  export interface ItemPedido {
    tituloItem: string;
    valorItem: number;
  }