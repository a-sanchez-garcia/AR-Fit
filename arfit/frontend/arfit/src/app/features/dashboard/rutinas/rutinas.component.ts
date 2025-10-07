import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { WorkoutService, Workout, WorkoutExercise } from '../../../services/workout.service';
import { HttpClient } from '@angular/common/http';

interface ExternalExercise {
  id: string;
  name: string;
}

@Component({
  selector: 'app-rutinas',
  templateUrl: './rutinas.component.html',
  styleUrls: ['./rutinas.component.scss']
})
export class RutinasComponent implements OnInit {

  rutinaForm: FormGroup;
  workouts: (Workout & { showConfig?: boolean; showAddExercise?: boolean })[] = [];
  exercisesList: ExternalExercise[] = [];
  loading = false;

  showCreateForm = false;

  newExercise: WorkoutExercise = { externalExerciseId: '', orderNumber: 1, plannedSets: 1 };

  constructor(
    private fb: FormBuilder,
    private workoutService: WorkoutService,
    private http: HttpClient
  ) {
    this.rutinaForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      exercises: this.fb.array([])
    });
  }

  ngOnInit() {
    this.loadWorkouts();
    this.loadExercises();
  }

  get exercises(): FormArray {
    return this.rutinaForm.get('exercises') as FormArray;
  }

  addExercise() {
    const exerciseGroup = this.fb.group({
      externalExerciseId: ['', Validators.required],
      plannedSets: [1, Validators.required],
      orderNumber: [1, Validators.required]
    });
    this.exercises.push(exerciseGroup);
  }

  removeExercise(index: number) {
    this.exercises.removeAt(index);
  }

  submitForm() {
    if (this.rutinaForm.invalid) return;

    this.loading = true;
    const data = {
      title: this.rutinaForm.value.title,
      description: this.rutinaForm.value.description,
      exercises: this.rutinaForm.value.exercises
    };

    this.workoutService.createWorkout(data).subscribe({
      next: () => {
        this.loading = false;
        this.rutinaForm.reset();
        this.exercises.clear();
        this.showCreateForm = false;
        this.loadWorkouts();
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
      }
    });
  }

  loadWorkouts() {
    this.workoutService.getWorkouts().subscribe(data => {
      this.workouts = data.map(w => ({
        ...w,
        showConfig: false,
        showAddExercise: false
      }));
    });
  }

  loadExercises() {
    this.http.get<ExternalExercise[]>('http://localhost:8080/api/external-exercises')
      .subscribe(data => this.exercisesList = data);
  }

  deleteWorkout(workoutId: number) {
    this.workoutService.deleteWorkout(workoutId).subscribe(() => this.loadWorkouts());
  }

  addExerciseToRoutine(workoutId: number) {
    if (!this.newExercise.externalExerciseId) {
      alert('Debes seleccionar un ejercicio');
      return;
    }

    this.workoutService.addExercise(workoutId, this.newExercise).subscribe({
      next: () => {
        this.loadWorkouts();
        this.newExercise = { externalExerciseId: '', orderNumber: 1, plannedSets: 1 };
      },
      error: (err) => console.error(err)
    });
  }

  toggleCreateForm() {
    this.showCreateForm = !this.showCreateForm;
  }
}
