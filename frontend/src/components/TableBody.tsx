export const TableBody = ({
  id,
  itemName,
  expiryInMiliseconds,
  quantity,
  priceInPence,
}: {
  id: number;
  itemName: string;
  expiryInMiliseconds: number;
  quantity: number;
  priceInPence: number;
}) => {
  let expiryDate = new Date(expiryInMiliseconds).toLocaleDateString();
  return (
    <tbody>
      <tr>
        <td>{id}</td>
        <td>{itemName}</td>
        <td>{expiryDate}</td>
        <td>{quantity}</td>
        <td>{priceInPence}</td>
      </tr>
      <tr>
        <td>{id}</td>
        <td>{itemName}</td>
        <td>{expiryDate}</td>
        <td>{quantity}</td>
        <td>{priceInPence}</td>
      </tr>
    </tbody>
  );
};
