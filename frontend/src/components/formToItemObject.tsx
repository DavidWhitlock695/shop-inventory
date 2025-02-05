import { ItemObject } from "./ItemObject";

export const formToItemObject = (
  elements: HTMLFormControlsCollection,
  currentInventoryLength: number
) => {
  const name = (elements.namedItem("itemName") as HTMLInputElement).value;
  const expiry = new Date(
    (elements.namedItem("itemExpiry") as HTMLInputElement).value
  ).getTime();
  const quantity = parseInt(
    (elements.namedItem("itemQuantity") as HTMLInputElement).value
  );
  const price =
    parseInt((elements.namedItem("itemPrice") as HTMLInputElement).value) * 100;
  const newItem = new ItemObject(
    currentInventoryLength + 1,
    name,
    expiry,
    quantity,
    price
  );
  return newItem;
};
