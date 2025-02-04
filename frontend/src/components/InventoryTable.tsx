import { TableBody } from "./TableBody";
import { TableHeader } from "./TableHeader";

export const InventoryTable = ({ jsonData }: { jsonData: string }) => {
  let testId: number = 1;
  let testItemName: string = "myName";
  let testExpiry: number = 1756569600000;
  let testQuantity: number = 23;
  let testPriceInPence: number = 1000;
  return (
    <table>
      <TableHeader></TableHeader>
      <TableBody
        id={testId}
        itemName={testItemName}
        expiryInMiliseconds={testExpiry}
        quantity={testQuantity}
        priceInPence={testPriceInPence}
      ></TableBody>
    </table>
  );
};
