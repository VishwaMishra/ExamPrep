import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../../services/login.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatInputModule,
    MatFormFieldModule,
    FormsModule,
    
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginData={
    username:'',
    password:''
  }
  constructor(private snack:MatSnackBar, private login:LoginService){}

  ngOnInit(): void {
    
  }


  formSubmit(){
    // alert("Submitted Successfully");
    console.log(this.loginData);

    if(this.loginData.username.trim()=='' || this.loginData.username == null){
      this.snack.open('Username is required !! ','',{
        duration: 3000,
      });
      return;
    }
    if(this.loginData.password.trim()=='' || this.loginData.password == null){
      this.snack.open('Password is required !! ','',{
        duration: 3000,
      });
      return;
    }
    
    //request server to generate token

    this.login.generateToken(this.loginData).subscribe((data: any)=>{
        console.log('success');
        console.log(data);
    },
    (error)=>{
      console.log("Error !");
      console.log(error);
    }
    );
  }
}
