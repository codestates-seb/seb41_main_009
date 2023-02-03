import styled from 'styled-components';
import { MdMenu } from 'react-icons/md';
import { useState } from 'react';
import { MAIN, CATEGORIES } from '../../constants/Categories';
import Menu from '../molecules/sidbar/SidebarMenu';
import { SidebarMainButton } from '../atoms/Buttons';
import Selected from '../../styles/Selected';
import useSidebarStore from '../../store/sidebarStore';

const Sidebar = () => {
  const { currentTab, setCurrentTab } = useSidebarStore(state => state);
  const [isListHidden, setIsListHidden] = useState(true);

  const handleSideMenuClick = e => setCurrentTab(e.target.textContent);

  const handleMenuButtonClick = () => {
    setIsListHidden(!isListHidden);
  };

  const handleModalClick = () => {
    setIsListHidden(!isListHidden);
  };

  return (
    <Container>
      <MenuButton onClick={handleMenuButtonClick} isListHidden={isListHidden}>
        <MdMenu size="22" />
      </MenuButton>
      <MenuList isListHidden={isListHidden}>
        {MAIN.map(mainArr => {
          const [path, name] = mainArr;
          return currentTab === name ? (
            <SelectedButton key={name} to={path} onClick={handleSideMenuClick}>
              {name}
            </SelectedButton>
          ) : (
            <SidebarMainButton key={name} to={path} onClick={handleSideMenuClick}>
              {name}
            </SidebarMainButton>
          );
        })}
        {CATEGORIES.map(categoryArr => {
          const [category, ...tags] = categoryArr;
          return <Menu key={category} category={category} tags={tags} onClick={handleSideMenuClick} />;
        })}
      </MenuList>
      <Modal onClick={handleModalClick} isListHidden={isListHidden} />
    </Container>
  );
};

const Container = styled.aside`
  width: var(--sidebar-width);
  height: 100vh;
`;

const MenuList = styled.div`
  display: flex;
  position: fixed;
  z-index: 1;
  justify-content: flex-start;
  flex-direction: column;

  @media (max-width: 1179px) {
    display: ${props => (props.isListHidden ? 'none' : 'flex')};
    z-index: 101;
  }
`;

const SelectedButton = styled(SidebarMainButton)`
  ${Selected}
`;

const MenuButton = styled.button`
  display: none;
  position: fixed;
  z-index: 1;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: none;
  background-color: rgba(0, 0, 0, 0.1);

  @media (max-width: 1179px) {
    display: ${props => (props.isListHidden ? 'flex' : 'none')};
  }

  &:hover {
    cursor: pointer;
  }
`;

const Modal = styled.div`
  display: none;

  @media (max-width: 1179px) {
    display: ${props => (props.isListHidden ? 'none' : 'block')};
    width: 100vw;
    height: 100vh;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) scale(1);
    background-color: rgba(255, 255, 255, 0.7);
    z-index: 100;
  }
`;

export default Sidebar;
