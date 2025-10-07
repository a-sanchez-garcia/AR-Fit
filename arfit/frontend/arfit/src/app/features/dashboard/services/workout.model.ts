export interface WorkoutExternalExercise {
  id: number;
  externalExerciseId: string;
  orderNumber: number;
  plannedSets: number;
}

export interface Workout {
  id: number;
  title: string;
  description: string;
  exercises: WorkoutExternalExercise[];
}
