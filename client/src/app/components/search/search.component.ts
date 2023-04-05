import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  form!: FormGroup

  constructor(private fb: FormBuilder, private route: Router){ }

  ngOnInit(): void {

    this.form = this.createSearch()
      
  }

  createSearch(): FormGroup{
    return this.fb.group({
      search: this.fb.control<string>('')
    })
  }

  search(){
    const movie = this.form?.value["search"]
    console.log(movie)
    this.route.navigate(['/list', movie])
  }

}
