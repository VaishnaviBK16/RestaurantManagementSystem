import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantListComponent } from './restaurant-list/restaurant-list.component';
import { CreateRestaurantComponent } from './create-restaurant/create-restaurant.component';
import { UpdateRestaurantComponent } from './update-restaurant/update-restaurant.component';
import { RestaurantDetailsComponent } from './restaurant-details/restaurant-details.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { LogInComponent } from './log-in/log-in.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdServiceService } from './auth-gaurd-service.service';

const routes: Routes = [

  {path: 'restaurants', component: RestaurantListComponent},
  {path: 'create-restaurant', component: CreateRestaurantComponent},
  {path: '', redirectTo: 'restaurant', pathMatch: 'full'},
  {path: 'update-restaurant/:restaurantId', component: UpdateRestaurantComponent},
  {path: 'restaurant-details/:restaurantId', component: RestaurantDetailsComponent},
  {path: 'home' , component:HomeComponent},
  {path: 'about' , component:AboutComponent},
  {path: 'contact-us' , component:ContactUsComponent},
  {path: 'log-in' , component:LogInComponent},
  {path: 'logout' , component:LogoutComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
