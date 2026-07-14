import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { NotificationService } from '../../services/notification';

@Component({
  selector: 'app-notification',
  // Component-level providers create a new service instance for this component
  // (and its children) — isolated from the root injector singleton.
  providers: [NotificationService],
  imports: [AsyncPipe],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.css',
})
export class NotificationComponent {
  private notificationService = inject(NotificationService);
  message$ = this.notificationService.message$;

  showSample(): void {
    this.notificationService.show('Scoped NotificationService instance message');
  }
}
