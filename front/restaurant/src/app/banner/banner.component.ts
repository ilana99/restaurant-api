import { Component } from '@angular/core';
import { SearchbarComponent } from '../searchbar/searchbar.component';

@Component({
  selector: 'app-banner',
  standalone: true,
  imports: [SearchbarComponent],
  templateUrl: './banner.component.html',
  styleUrl: './banner.component.sass'
})
export class BannerComponent {

}
