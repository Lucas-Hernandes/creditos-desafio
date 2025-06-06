import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { ConsultaCreditosComponent } from './app/consulta-creditos/consulta-creditos.component';
import { FormsModule } from '@angular/forms';

bootstrapApplication(ConsultaCreditosComponent, {
  providers: [
    importProvidersFrom(FormsModule),
    provideHttpClient(),
    provideAnimations()
  ]
});
