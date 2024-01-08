import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatInputModule,
    MatFormFieldModule,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  public user={
    username:'',
    password:''
  }


  formSubmit(){
    // alert("Submitted Successfully");
    console.log(this.user);

    if(this.user.username=='' || this.user.username == null){
      alert('Username is required!!');
      return;
    }
  }
}
