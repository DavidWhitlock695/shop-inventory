export const DeleteItemControl = ({
  handleSubmitDelete,
}: {
  handleSubmitDelete: (form: React.FormEvent<HTMLFormElement>) => void;
}) => {
  return (
    <div>
      <h3>Delete Entry</h3>
      <form
        action="DELETE"
        className="controlPanelSection"
        id="deleteSection"
        onSubmit={(form) => handleSubmitDelete(form)}
      >
        <label className="controlPanelLabel" htmlFor="itemID">
          Row:
        </label>
        <input
          className="controlPanelInput"
          type="number"
          id="row"
          name="row"
          min={0}
          step={1}
          required={true}
        />
        <div className="submissionBox">
          <input type="submit"></input>
        </div>
      </form>
    </div>
  );
};
