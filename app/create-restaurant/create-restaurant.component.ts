import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';

@Component({
  selector: 'app-create-restaurant',
  templateUrl: './create-restaurant.component.html',
  styleUrls: ['./create-restaurant.component.css']
})
export class CreateRestaurantComponent {

  restaurant: Restaurant = new Restaurant();
  constructor(private restaurantService: RestaurantService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveRestaurant(){
    this.restaurantService.createRestaurant(this.restaurant).subscribe( data =>{
      console.log(data);
      this.goToRestaurantList();
    },
    error => console.log(error));
  }

  goToRestaurantList(){
    this.router.navigate(['/restaurants']);
  }
  
  onSubmit(){
    console.log(this.restaurant);
    this.saveRestaurant();
  }
}
