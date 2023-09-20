export class Horario {
  id: number;
  status: 'Livre' | 'Ocupado';
  data: Date;

  constructor(data: Date) {
      this.id = 0;
      this.status = 'Livre';
      this.data = data;
  }
}