import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Comment {
  id: number;
  username: string;
  content: string;
  createdAt: string;
}

export interface Post {
  id: number;
  username: string;
  content: string;
  image?: string;
  video?: string;
  createdAt: string;
  likesCount: number;
  commentsCount: number;
  comments: Comment[];
}

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = 'http://localhost:8080/api/posts';

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }

  getPersonalizedFeed(username: string): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.baseUrl}/feed?username=${username}`);
  }

  createPost(username: string, content: string, image?: string, video?: string): Observable<Post> {
    return this.http.post<Post>(`${this.baseUrl}?username=${username}`, { content, image, video });
  }

  deletePost(username: string, postId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${postId}?username=${username}`);
  }
}
