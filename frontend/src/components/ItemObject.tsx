export class ItemObject {
  id: number;
  itemName: string;
  expiryDateddmmyyyy: string;
  quantity: number;
  priceInPence: number;

  constructor(
    id: number,
    itemName: string,
    expiryDateddmmyyyy: string,
    quantity: number,
    priceInPence: number
  ) {
    (this.id = id),
      (this.itemName = itemName),
      (this.expiryDateddmmyyyy = expiryDateddmmyyyy),
      (this.quantity = quantity),
      (this.priceInPence = priceInPence);
  }
}
