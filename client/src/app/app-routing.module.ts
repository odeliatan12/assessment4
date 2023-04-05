import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MoviereviewlistComponent } from './components/moviereviewlist/moviereviewlist.component';
import { PostCommentComponent } from './components/post-comment/post-comment.component';
import { SearchComponent } from './components/search/search.component';

const routes: Routes = [
  { path: "", component: AppComponent},
  { path: "search", component: SearchComponent},
  { path: "list/:movieReview", component: MoviereviewlistComponent},
  { path: "comments/:movieName", component: PostCommentComponent},
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
