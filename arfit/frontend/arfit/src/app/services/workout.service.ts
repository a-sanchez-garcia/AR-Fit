import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Workout {
  id: number;
  title: string;
  description: string;
  exercises?: WorkoutExercise[];
}

export interface WorkoutExercise {
  externalExerciseId: string;
  orderNumber: number;
  plannedSets: number;
}

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {

  private baseUrl = 'http://localhost:8080/api/workouts';

  constructor(private http: HttpClient) {}

  // Listar rutinas del usuario
  getWorkouts(): Observable<Workout[]> {
    return this.http.get<Workout[]>(`${this.baseUrl}`);
  }

  // Crear nueva rutina
  createWorkout(data: { title: string, description: string, exercises: WorkoutExercise[] }): Observable<Workout> {
    return this.http.post<Workout>(`${this.baseUrl}`, data);
  }

  // Eliminar rutina
  deleteWorkout(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  // Añadir ejercicio a rutina existente
  addExercise(workoutId: number, exercise: WorkoutExercise): Observable<WorkoutExercise> {
    return this.http.post<WorkoutExercise>(`${this.baseUrl}/${workoutId}/external-exercises`, exercise);
  }

}
