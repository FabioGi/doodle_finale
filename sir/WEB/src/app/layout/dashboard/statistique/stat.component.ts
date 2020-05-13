import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'stat',
    templateUrl: './stat.component.html',
    styleUrls: ['./stat.component.scss']
})
export class StatistiqueComponent implements OnInit {
    @Input() bgClass: string;
    @Input() icon: string;
    @Input() count: number;
    @Input() countValided: number;
    @Input() countNovalided: number;
    @Input() labeltotal: string;
    @Input() labelValided: string;
    @Input() labelNoValided: string;
    // @Input() data: number;

    constructor() {}

    ngOnInit() {}
}
