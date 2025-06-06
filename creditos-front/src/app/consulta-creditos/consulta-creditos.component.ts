import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-consulta-creditos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './consulta-creditos.component.html',
  styleUrls: ['./consulta-creditos.component.scss']
})
export class ConsultaCreditosComponent {
  numeroNfse = '';
  numeroCredito = '';
  creditos: any[] = [];
  erro = '';

  constructor(private http: HttpClient) {}

  buscarPorNfse() {
    this.http.get<any[]>(`http://localhost:8080/api/creditos/${this.numeroNfse}`)
      .subscribe({
        next: data => {
          this.creditos = data;
          this.erro = '';
        },
        error: () => {
          this.creditos = [];
          this.erro = 'Nenhum crédito encontrado com este número de NFS-e.';
        }
      });
  }

  buscarPorCredito() {
    this.http.get<any>(`http://localhost:8080/api/creditos/credito/${this.numeroCredito}`)
      .subscribe({
        next: data => {
          this.creditos = [data];
          this.erro = '';
        },
        error: () => {
          this.creditos = [];
          this.erro = 'Crédito não encontrado com este número.';
        }
      });
  }
}
