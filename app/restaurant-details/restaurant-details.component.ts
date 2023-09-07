import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestaurantService } from '../restaurant.service';
import { Restaurant } from '../restaurant';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent {

  restaurantId: number = 0;
  restaurant: any = [];
  location: any;
  constructor(private route: ActivatedRoute, private restaurantService: RestaurantService) { }

  ngOnInit(): void {
    this.restaurantId = this.route.snapshot.params['restaurantId'];

    this.restaurant = new Restaurant();
    this.restaurantService.getRestaurantById(this.restaurantId).subscribe( data => {
    this.restaurant = data;
    });
  }

  goBack(): void {
    this.location.back();
  }
}



