import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { CreateTaskComponent } from './components/create-task/create-task.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { UserLoginComponent } from './components/user-login/user-login.component';

const routes: Routes = [
  {path: "employees",component: EmployeeListComponent},
  {path: "create-employee", component: CreateEmployeeComponent},
  {path: "update-employee/:id", component: UpdateEmployeeComponent},
  {path: "employee-details/:id", component:EmployeeDetailsComponent},
  {path: "tasks", component:TaskListComponent},
  {path: "create-tasks", component:CreateTaskComponent },
  {path: "user-login", component:UserLoginComponent},
  {path: "", redirectTo: "user-login", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
