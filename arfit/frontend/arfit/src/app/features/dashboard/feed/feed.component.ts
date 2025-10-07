import { Component, OnInit } from '@angular/core';
import { PostService, Post, Comment } from '../../../services/post.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  posts: Post[] = [];
  username = 'alberto'; // reemplazar por el username del usuario logueado
  loading = false;
  newPostContent = '';

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.loadFeed();
  }

  loadFeed() {
    this.loading = true;
    this.postService.getPersonalizedFeed(this.username).subscribe({
      next: data => {
        this.posts = data;
        this.loading = false;
      },
      error: err => {
        console.error(err);
        this.loading = false;
      }
    });
  }

  createPost() {
    if (!this.newPostContent.trim()) return;
    this.postService.createPost(this.username, this.newPostContent).subscribe({
      next: post => {
        this.posts.unshift(post);
        this.newPostContent = '';
      },
      error: err => console.error(err)
    });
  }

  deletePost(postId: number) {
    this.postService.deletePost(this.username, postId).subscribe({
      next: () => {
        this.posts = this.posts.filter(p => p.id !== postId);
      },
      error: err => console.error(err)
    });
  }
}
