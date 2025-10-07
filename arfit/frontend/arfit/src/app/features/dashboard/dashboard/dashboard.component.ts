import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  constructor(private authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout().subscribe({
      next: () => {
        this.clearSessionAndRedirect();
      },
      error: (err) => {
        console.warn('Error cerrando sesión (probablemente cookie HTTPS):', err);
        this.clearSessionAndRedirect(); // 🔧 continuar aunque falle
      }
    });
  }

  private clearSessionAndRedirect() {
    localStorage.removeItem('accessToken');
    sessionStorage.clear();
    this.router.navigate(['/auth/login']);
  }
}
