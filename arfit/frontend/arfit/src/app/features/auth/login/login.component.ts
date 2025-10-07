import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    const dto = {
      username: this.username,
      password: this.password
    };
    this.authService.login(dto).subscribe({
      next: (res) => {
        console.log('Login correcto', res);
        // Guardar token, redirigir a home u otra ruta
        localStorage.setItem('accessToken', res.accessToken);
        this.router.navigate(['/dashboard']); // ✅ redirige al dashboard
      },
      error: (err) => {
        console.error('Error login:', err);
        alert('Usuario o contraseña incorrectos');
      }
    });
  }
}
