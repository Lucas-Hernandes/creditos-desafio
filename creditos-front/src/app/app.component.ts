import { Component } from '@angular/core';
import { ConsultaCreditosComponent } from "./consulta-creditos/consulta-creditos.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ConsultaCreditosComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'creditos-front';
}
