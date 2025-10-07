import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  username = '';
  email = '';
  password = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    const dto = {
      username: this.username,
      email: this.email,
      password: this.password
    };
    this.authService.register(dto).subscribe({
      next: (res) => {
        console.log('Registro correcto', res);
        // Puedes redirigir al login automáticamente
        this.router.navigate(['/auth/login']);
      },
      error: (err) => {
        console.error('Error al registrar:', err);
        alert('No se pudo registrar. Verifique los datos.');
      }
    });
  }
}
