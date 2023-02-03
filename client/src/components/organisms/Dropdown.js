import styled from 'styled-components';
import { useState } from 'react';

import useContentCreateStore from '../../store/contentCreateStore';
import { TAGS } from '../../constants/Categories';
import { LabelMedium } from '../../styles/typo';

const Dropdown = ({ padding }) => {
  const { categoryName, setCategoryKey, setCategoryName } = useContentCreateStore();
  const [isOpen, setIsOpen] = useState(false);

  const toggleOpen = () => {
    setIsOpen(!isOpen);
  };

  const handleSelectTag = event => {
    setCategoryKey(event.target.getAttribute('data-key'));
    setCategoryName(event.target.textContent);
    setIsOpen(false);
  };

  return (
    <Container>
      <SelectedTagname padding={padding} onClick={toggleOpen}>
        {categoryName}
      </SelectedTagname>
      {isOpen ? (
        <DropdownListContainer>
          {TAGS.map(el => {
            return (
              <DropdownItem onClick={handleSelectTag} key={el[0]} data-key={el[0]}>
                {el[1]}
              </DropdownItem>
            );
          })}
        </DropdownListContainer>
      ) : null}
    </Container>
  );
};

const Container = styled.div`
  ${LabelMedium};
  cursor: pointer;
  position: relative;
  z-index: 103;
`;

const DropdownListContainer = styled.ul`
  width: 100%;
  position: absolute;
  display: flex;
  flex-direction: column;
  top: 70px;
  left: 0px;
  height: 240px;
  overflow-x: hidden;
  overflow-y: auto;
  background-color: white;
  border: 2px solid black;
  box-shadow: var(--boxShadow-00) black;
`;

const DropdownItem = styled.li`
  color: black;
  width: 100%;
  height: 60px;
  padding: 10px;
  cursor: pointer;
  border-bottom: 1px solid var(--gray-200);
`;

const SelectedTagname = styled.div`
  width: 100%;
  font-weight: 700;
  color: white;
  background-color: rgba(51, 51, 51, 1);
  box-sizing: border-box;
  padding: ${props => props.padding || '20px'};
`;

export default Dropdown;
