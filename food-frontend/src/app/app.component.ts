import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PagamentoDetalhesDTO } from 'src/interfaces/dto';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'food-frontend';

  detalhesPagamento: any;

  formData: any = {
    valorFrete: 0,
    tipoAcrescimo: 'INTEIRO',
    tipoDesconto: 'INTEIRO',
    tipoPagamento: 'PIX',
    desconto: 0,
    acrescimo: 0,
    pedidos: [
      {
        nomeSolicitante: '',
        itensPedido: [{ tituloItem: '', valorItem: 0 }]
      }
    ]
  };



  constructor(private http: HttpClient) { }

  submitForm() {
    const endpoint = 'http://localhost:8080/api/v1/food/processarPedido';

    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    this.formData.pedidos = this.formData.pedidos.filter((pedido: { nomeSolicitante: string }) => pedido.nomeSolicitante && pedido.nomeSolicitante.trim() !== '');

    this.formData.pedidos.forEach((pedido: { itensPedido: any[] }) => {
      pedido.itensPedido = pedido.itensPedido.filter(item => item.tituloItem && item.tituloItem.trim() !== '');
    });

    this.http.post<PagamentoDetalhesDTO[]>(endpoint, this.formData, { headers: headers })
      .subscribe(
        (response: PagamentoDetalhesDTO[]) => {
          console.log('Resposta do backend:', response);
          this.detalhesPagamento = response;
        },
        (error) => {
          console.error('Erro ao enviar requisição:', error);
          alert('Erro ao enviar requisição. Por favor, tente novamente.');
        }
      );
  }

  onChangeTipoDesconto(event: any) {
    this.formData.tipoDesconto = event.target.value;
  }

  onChangeTipoAcrescimo(event: any) {
    this.formData.tipoAcrescimo = event.target.value;
  }

  onChangeTipoPagamento(event: any) {
    this.formData.tipoPagamento = event.target.value;
  }

  onInputValorFrete(event: any) {
    this.formData.valorFrete = event.target.value;
  }

  onInputDesconto(event: any) {
    this.formData.desconto = event.target.value;
  }

  onInputAcrescimo(event: any) {
    this.formData.acrescimo = event.target.value;
  }

  adicionarPedido() {
    const ultimoPedido: any = this.formData.pedidos[this.formData.pedidos.length - 1];
    if (ultimoPedido.nomeSolicitante.trim() !== '') {
      this.formData.pedidos.push({
        nomeSolicitante: '',
        itensPedido: [{ tituloItem: '', valorItem: 0 }]
      });
    }

    console.log(this.formData.pedidos)
  }

  adicionarItem(pedido: any) {
    pedido.itensPedido.push({ tituloItem: '', valorItem: 0 });
  }

}